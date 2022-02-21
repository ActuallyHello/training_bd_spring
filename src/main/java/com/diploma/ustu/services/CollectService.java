package com.diploma.ustu.services;

import com.diploma.ustu.generators.ExcelData;
import com.diploma.ustu.models.Entities.Attribute;
import com.diploma.ustu.models.Entities.EntityDB;
import com.diploma.ustu.repo.AttributeRepo;
import com.diploma.ustu.repo.EntityDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CollectService {

    @Autowired
    private EntityDBRepo entityDBRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    /**
     *  Маппа которая содержит в себе:
     *      ключ - сущность из БД
     *      значение - маппа которая содержит в себе:
     *              ключ - атрибут определенной сущности из БД
     *              значение - коллекция данные о каждому атриубте
     *  Сущность - Сотрудник
     *      Атрибут - id / Данные - 1, 2, 3
     *      Атрибут - имя / Данные - Коля, Петя, Вася
     */
    private final Map<EntityDB, Map<Attribute, List<String>>> entityAttributeMap = new HashMap<>();

    //TODO:
    // 1. collectEntities по определенной модели

    /*
        Получаем сущности - ключи в мапе. Для значений делаем новую мапу
        Получаем атрибуты сущности - ключи вложенной мапы. Значения - данные с экселя
        Возвращаем исходную мапу
     */

    /**
     *
     * @param entities
     * @param sizeList
     */
    public void collectEntities(List<EntityDB> entities, List<Integer> sizeList) {
        for (int i = 0; i < entities.size(); i++) {
            collectDataInSingleEntity(entities.get(i), sizeList.get(i));
        }
        System.out.println(entityAttributeMap);
    }

    /**
     * Метод заполняющий определенный объект сущности данными из excel
     * Устанавливает ключ - объект сущности
     * Все атрибуты сущности устанавливаются в ключ вложенной маппы
     * Для каждого атрибута из ExcelData возвращает коллекцию строк определенного размера по названию атрибута
     * @param entity сущность из БД
     * @param size размер выборки из excel для каждого атрибута
     */
    public void collectDataInSingleEntity(EntityDB entity, int size) {
        entityAttributeMap.put(entity, new HashMap<>());
        List<Attribute> attributeList = attributeRepo.findAttributeByIdEntity(entity.getIdEntity());
        Map<Attribute, List<String>> attributeEntry = entityAttributeMap.get(entity);
        for (Attribute attribute : attributeList) {
            List<String> dataFromExcel = ExcelData.getTestDataByKey(
                    attribute.getNameAttribute()
                            .toLowerCase(Locale.ROOT)
                            .replaceAll("_.+|\\s.+", ""),
                    size
            );
            attributeEntry.put(attribute, dataFromExcel);
        }
        System.out.println(entityAttributeMap);
        //log.info("ADD ENTITY - " + entity);
        //log.info("entityAttributeMap = " + entityAttributeMap);
    }

    //TODO:
    // 1. relatedToFK -> findFirst().get -> заменить на orElse
    // 2. заменить attributeName.contains(fk) на attributeName.startWith("fk");

    /*
        Берем из мапы каждую пару Entity, <Attribute, List>
        Для каждого Entity в списке берем пару Attribute, List
            Если в паре встретился атрибут с именем "fk", значит к нему нужно подставить соответствующие значения id другой сущности
            Находим имя сущности извлекая все, что после fk (гарантируется, что после fk идёт имя сущности, на которую он ссылается (fk_company))
            Берем еще один внутренний цикл по всем сущностям
            Если имя сущности совпало с той, которая была ссылающейся fk, то вынимает оттуда атрибут с именем ID (id_company)
            озвращаемся к той паре Attribute, List, где вёлся поиск и заменяем List значений fk_company на найденные в id_company
            выходим из цикла
        Company {id company = [1, 2, 3]} Employee {id, fk_company = []}
        В первой сущности нет fk - пропускаем. Во второй сущности есть fk_company
            убираем fk_, в entityName -> company
            в цикле ищем сущность c именем company
            нашли, значит забираем relatedToFK => id_company
            в той паре Attribute List заменяем value (key: Attribute, value: List) на то что нашли через relateToFK (get возвращает значение)
        получаем Company {id company = [1, 2, 3]} Employee {id, fk_company = [1, 2, 3]}
     */

    //TODO:
    // 1: разбить на функции


    /**
     * Итерируемся по каждому атрибуту определенной сущности в поисках преписки fk
     * Извлекаем название сущности из имени атрибута (гарантируется, что после преписки fk будет следовать имя сущности на которое fk ссылается)
     * Итерируемся по всем сущностям в поисках совпадений с извлеченным названием \\ альтернатива сделать запрос в БД по имени сущности
     * У найденной сущности извлекаем данные принадлежащие атрибуту с препиской id и вставляем их в коллекцию атриубта с fk
     *  (симулируя прямую ссылку с fk к id)
     * До метода:               Корпорация - { id = [1], fk_сотрудник = [] }, Сотрудник - {id = [1, 2, 3]}
     * После выполнения метода: Корпорация - { id = [1], fk_сотрудник = [1, 2, 3] }, Сотрудник - {id = [1, 2, 3]}
     */
    public void insertIdToFK() {
        for (Map.Entry<EntityDB, Map<Attribute, List<String>>> entryEntity : entityAttributeMap.entrySet()) { // по каждой сущности
            for (Map.Entry<Attribute, List<String>> entryAttribute : entryEntity.getValue().entrySet()) { // по каждому ентри сущности
                String attributeName = entryAttribute.getKey().getNameAttribute();
                if (attributeName.contains("fk ") || attributeName.contains("fk_")) {
                    String entityName = attributeName.toLowerCase(Locale.ROOT).replaceAll("fk_|fk\\s", ""); // убираем fk, чтобы найти сущность
                    for (Map.Entry<EntityDB, Map<Attribute, List<String>>> innerEntryEntity: entityAttributeMap.entrySet()) { // цикл по всем сущностям для проверки
                        if (innerEntryEntity.getKey().getNameEntity().equalsIgnoreCase(entityName)) { // забираем атрибут у которого есть id
                            Attribute relatedToFK = innerEntryEntity.getValue().keySet()
                                    .stream()
                                    .filter(x -> x.getNameAttribute().startsWith("id"))
                                    .findFirst().get();
                            entryAttribute.setValue(innerEntryEntity.getValue().get(relatedToFK)); // в том ентри, в котором искали fk, заменяем List,
                                                                                                        // на тот List который находится под сущностью с атрибутом id
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(entityAttributeMap);
    }

    /**
     * Собираем все сущности, их атрибуты и значения атрибутов в отформатированном виде
     * @return - коллекция значений отображающий результат работы методов
     */
    private List<String> collectToView() {
        List<String> lines = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean onlyForFirstEntity;
        for (EntityDB entity : entityAttributeMap.keySet()) {
            builder.append(String.format("Сущность: %12s", entity.getNameEntity())).append(" | ");
            onlyForFirstEntity = true;
            for (Map.Entry<Attribute, List<String>> entryAttribute : entityAttributeMap.get(entity).entrySet()) {
                if (onlyForFirstEntity) {
                    onlyForFirstEntity = false;
                } else {
                    builder.append(String.format("%22s", " ")).append(" | ");
                }
                builder.append(String.format("Атрибут: %16s",entryAttribute.getKey().getNameAttribute()))
                        .append(" | ")
                        .append(entryAttribute.getValue())
                        .append("\n");

            }
            lines.add(builder.toString());
            builder = new StringBuilder();
        }
        return lines;
    }

    /**
     * Запись в файл полувшихся данных
     *
     * @param whichOne - файл в который будет запись
     * @throws IOException - исключение :)))
     */

    public void writeToFile(String whichOne) throws IOException {
        File output = new File("D:\\ДИПЛОМ\\SpringDiploma\\ustu\\src\\main\\resources\\data\\" + whichOne);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
            List<String> lines = collectToView();
            StringJoiner stringJoiner = new StringJoiner("\n", "[", "]");
            for (String str : lines) {
                stringJoiner.add(str);
            }
//            System.out.println(stringJoiner.toString());
            writer.write(stringJoiner.toString());
        }
    }


    // old version just for some reason


    /*
     *  на входе получает сущность, которую ищет в mapEntityAttributeValues
     *  преобразует результат в stream<Attribute>
     *  считаем сколько совпадений с 'fk_' или 'fk '
     */
//    private int countForeignKeys(EntityDB entityKey) {
//        return (int) mapEntityAttributesValues.get(entityKey)
//                .keySet()
//                .stream()
//                .map(Attribute::getNameAttribute)
//                .filter(x -> x.toLowerCase(Locale.ROOT).contains("fk_") ||
//                        x.toLowerCase(Locale.ROOT).contains("fk "))
//                .count();
//    }
//
//    /*
//     * сортируем по возрастанию мапу по условию возрастания количества FK
//     */
//    private void sortMapByFKNaturalOrder() {
//        // https://stackoverflow.com/questions/58998826/java-stream-collect-to-treemap-in-reverse-order
//        // 4 параметр, который можно кастамизировать под сортировку всей структуры мапы
//        Supplier<TreeMap<EntityDB, Map<Attribute, List<String>>>> myMapSupplier = () -> new TreeMap<>((o1, o2) -> {
//            int count_o1 = countForeignKeys(o1);
//            int count_o2 = countForeignKeys(o2);
//            return count_o1 - count_o2;
//        });
//
//        mapEntityAttributesValues = mapEntityAttributesValues.entrySet()
//                .stream()
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (a, b) -> a,
//                        myMapSupplier
//                ));
//    }

}
