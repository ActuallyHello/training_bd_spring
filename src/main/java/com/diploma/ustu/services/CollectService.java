package com.diploma.ustu.services;

import com.diploma.ustu.generators.ExcelData;
import com.diploma.ustu.models.Entities.Attribute;
import com.diploma.ustu.models.Entities.EntityDB;
import com.diploma.ustu.repo.AttributeRepo;
import com.diploma.ustu.repo.EntityDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/*
    Собираем сущности из определенной и инсертим тестовые данные из экселя
 */

@Slf4j
@Service
public class CollectService {

    @Autowired
    private EntityDBRepo entityDBRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    private final Map<EntityDB, Map<Attribute, List<String>>> entityAttributeMap = new HashMap<>();

    //TODO:
    // 1. collectEntities по определенной модели

    /*
        Получаем сущности - ключи в мапе. Для значений делаем новую мапу
        Получаем атрибуты сущности - ключи вложенной мапы. Значения - данные с экселя
        Возвращаем исходную мапу
     */
    public void collectEntities(List<EntityDB> entities, List<Integer> sizeList) {
        for (int i = 0; i < entities.size(); i++) {
            collectDataInSingleEntity(entities.get(i), sizeList.get(i));
        }
        System.out.println(entityAttributeMap);
    }

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
}
