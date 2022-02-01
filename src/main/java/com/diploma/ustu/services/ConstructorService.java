package com.diploma.ustu.services;

import com.diploma.ustu.generators.ExcelData;
import com.diploma.ustu.models.Entities.Attribute;
import com.diploma.ustu.models.Entities.EntityDB;
import com.diploma.ustu.repo.AttributeRepo;
import com.diploma.ustu.repo.EntityDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/*
 *  Построение денормализованных данных посредством соединения нормализованных
 *  Пока насчёт оптимизации не паримся
 */
@Service
public class ConstructorService {

    @Autowired
    private EntityDBRepo entityDBRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    //TODO:
    // 1. Отсортированные сущности заполнить данными
    // 2. Каждому FK присвоить соответсвующее значение id из таблицы на которую ссылаются


    /*
     * Существует структура хранения сущности, её атрибутов и значение этих атрибутов
     * Представлена в виде мапы, ключ которой хранит сущность, которая ссылается на несколько своих атрибутов
     * значение ключа мапа в которой ключом выступает название атрибута, а значением лист значений для этих атрибутов
     * сущность - Сотрудник, атрибуты - Сотрудник = [id = [1,2,3], имя = [Артём, Валера, Саша], должность = [Инженер, Инженер, Директор]]
     * Map<Сотрудник, (Map<id, [1,2,3]>,Map<имя,[...], ...)
     * Map<EntityDB, Map<Attribute, List<String>>> mapEntityAttributesValues
     */
    private Map<EntityDB, Map<Attribute, List<String>>> mapEntityAttributesValues = new HashMap<>();

    /*
     *  на входе получает сущность, которую ищет в mapEntityAttributeValues
     *  преобразует результат в stream<Attribute>
     *  считаем сколько совпадений с 'fk_' или 'fk '
     */
    private int countForeignKeys(EntityDB entityKey) {
        return (int) mapEntityAttributesValues.get(entityKey)
                .keySet()
                .stream()
                .map(Attribute::getNameAttribute)
                .filter(x -> x.toLowerCase(Locale.ROOT).contains("fk_") ||
                        x.toLowerCase(Locale.ROOT).contains("fk "))
                .count();
    }

    /*
     * сортируем по возрастанию мапу по условию возрастания количества FK
     */
    private void sortMapByFKNaturalOrder() {
        // https://stackoverflow.com/questions/58998826/java-stream-collect-to-treemap-in-reverse-order
        // 4 параметр, который можно кастамизировать под сортировку всей структуры мапы
        Supplier<TreeMap<EntityDB, Map<Attribute, List<String>>>> myMapSupplier = () -> new TreeMap<>((o1, o2) -> {
            int count_o1 = countForeignKeys(o1);
            int count_o2 = countForeignKeys(o2);
            return count_o1 - count_o2;
        });

        mapEntityAttributesValues = mapEntityAttributesValues.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        myMapSupplier
                ));
    }

    /*
     *  получаем все сущности модели
     *  каждую устанавливаем ключом
     *  получаем ее атрибуты
     *  каждый атрибут делаем ключом во внутрнней коллекции
     *  каждому атрибуту присваиваем лист значений с экселя
     *  сортируем
     */
    public void collectEntities() {
        List<EntityDB> entityDBList = entityDBRepo.findAll(); // получаем все сущности определенной модели
        for (EntityDB entity : entityDBList) { // итерируемся по каждой
            mapEntityAttributesValues.put(entity, new HashMap<>()); // кладём ключ и создаем для него новую структуру
            List<Attribute> attributeList = attributeRepo.findAttributeByIdEntity(entity.getIdEntity()); // получаем все атрибуты по определенной сущности
            Map<Attribute, List<String>> entry = mapEntityAttributesValues.get(entity); // обращаемся от Entity к мапе, содержащей Attribute, List<String>
            for (Attribute attribute : attributeList) { // для каждого имени атрибута (по каждому первому слову разделенного либо ' ' либо '_' получаем значение из excel
                List<String> excel_data = ExcelData.getTestDataByKey(attribute.getNameAttribute().toLowerCase(Locale.ROOT).replaceAll("_.+|\\s.+", ""));
                entry.put(attribute, excel_data); // добавляем к атрибуту его значения
            }
        }
        //sortMapByFKNaturalOrder(); not needed now
        System.out.println(mapEntityAttributesValues);
    }

    // другой путь - создать класс с сущностью и ее атрибутами
    // сделать список объектов этого класса, в котором реализовать метод подсчёт FK
    // Collections.sort(cached_entityWithAttributesList, Comparator.comparingInt(EntityWithAttributes::countFK));


    public void insertIdToFK() {
        for (Map.Entry<EntityDB, Map<Attribute, List<String>>> entry : mapEntityAttributesValues.entrySet()) {
            for (Map.Entry<Attribute, List<String>> innerEntry : entry.getValue().entrySet()) {
                String attributeName = innerEntry.getKey().getNameAttribute();
                if (attributeName.contains("fk ") || attributeName.contains("fk_")) {
                    String entityName = attributeName.toLowerCase(Locale.ROOT).replaceAll("fk_|fk\\s", "");
                    for (Map.Entry<EntityDB, Map<Attribute, List<String>>> secondEntry: mapEntityAttributesValues.entrySet()) {
                        if (secondEntry.getKey().getNameEntity().equalsIgnoreCase(entityName)) {
                            Attribute relatedToFK = secondEntry.getValue().keySet().stream().filter(x -> x.getNameAttribute().startsWith("id")).findFirst().get();
                            innerEntry.setValue(secondEntry.getValue().get(relatedToFK));
                        }
                    }
                }
            }
        }
        System.out.println(mapEntityAttributesValues);
    }
}
