package com.diploma.ustu.services;

import com.diploma.ustu.generators.ExcelData;
import com.diploma.ustu.models.Entities.Attribute;
import com.diploma.ustu.models.Entities.EntityOf;
import com.diploma.ustu.repo.AttributeRepo;
import com.diploma.ustu.repo.EntityOfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/*
 *  Построение денормализованных данных посредством соединения нормализованных
 *  Пока насчёт оптимизации не паримся
 */
@Service
public class ConstructorService {

    @Autowired
    private EntityOfRepo entityOfRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    /*
     * Существует структура хранения сущности, её атрибутов и значение этих атрибутов
     * Представлена в виде мапы, ключ который хранит сущность, которая ссылается на несколько своих атрибутов
     * значение ключа мапа в которой ключом выступает название атрибута, а значением лист значений для этих атрибутов
     * сущность - Сотрудник, атрибуты - Сотрудник[id, имя, должность]
     * значения для каждого атрибута id: [1,2,3], имя: [Артём, Валера, Саша] , должность: [Инженер, Инженер, Директор]
     * Map<Сотрудник, (Map<id, [1,2,3]>,Map<имя,[...], ...)
     */
    private Map<EntityOf, Map<Attribute, List<String>>> entities_attributes_values = new HashMap<>();
    private Map<String, Map<String, List<String>>> testik = new HashMap<>();

    public void test() {
        String[] entities = {"1 entity"};
        String[] attributes = {"1.1 attribute", "1.2 attribute"};

        for (String entity : entities) {
            testik.put(entity, new HashMap<>());
            for (Map<String, List<String>> entry : testik.values()) {
                for (String attribute : attributes) {
                    entry.put(attribute, null);
                }
            }
        }
        System.out.println(testik);
    }



    public void construct() {
        List<EntityOf> entityOfList = entityOfRepo.findAll(); // получаем все сущности определенной модели
        for (EntityOf entity : entityOfList) { // итерируемся по каждой
            entities_attributes_values.put(entity, new HashMap<>()); // кладём ключ и создаем для него новую структуру
            List<Attribute> attributeList = attributeRepo.findAttributeByIdEntity(entity.getIdEntity()); // получаем все атрибуты по определенной сущности
            Map<Attribute, List<String>> entry = entities_attributes_values.get(entity);
            for (Attribute attribute : attributeList) {
                List<String> excel_data = ExcelData.getTestDataByKey(attribute.getNameAttribute().toLowerCase(Locale.ROOT).replaceAll("_.+|\\s.+", ""));
                entry.put(attribute, excel_data);
            }
//            for (Map<Attribute, List<String>> first_entry : entities_attributes_values.values()) { // в только что созданном ключе
//                for (Attribute attribute : attributeList) {
//                    first_entry.put(attribute, new ArrayList<>());
//                }
//            }
        }
        System.out.println(entities_attributes_values);
    }
}
