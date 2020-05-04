package org.curtis.database;

import org.curtis.musicxml.common.XmlComment;
import org.curtis.musicxml.score.Score;

import java.lang.reflect.Field;
import java.util.List;

public class ItemOrderer {
    private ItemOrderer() {

    }

    public static void orderScoreItems(Score score) {
        orderDatabaseItem(score);
    }

    private static void orderDatabaseItem(DatabaseItem databaseItem) {
        Class databaseItemClass = databaseItem.getClass();
        Field[] fields = databaseItemClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object fieldObject = field.get(databaseItem);
                if (fieldObject == null) continue;
                if (fieldObject instanceof XmlComment) continue;
                if (fieldObject instanceof List) {
                    List objects = (List)fieldObject;
                    int orderCount = 0;
                    for (Object object : objects) {
                        if (!(object instanceof DatabaseItem)) break;
                        if (object instanceof OrderedItem) {
                            OrderedItem orderedItem = (OrderedItem)object;
                            orderCount++;
                            orderedItem.setOrdering(orderCount);
                        }
                        orderDatabaseItem((DatabaseItem)object);
                    }
                } else if (fieldObject instanceof DatabaseItem) {
                    DatabaseItem databaseItemField = (DatabaseItem) fieldObject;
                    orderDatabaseItem(databaseItemField);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
