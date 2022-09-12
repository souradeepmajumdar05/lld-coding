package Trello.repository;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RepositoryUtility {

    //************Utlity section*************//
    public static boolean isEmpty(Map map){
      return map.size()>0?false:true;
    }
    static String getId(Map<Integer, String> idMap) {
        AtomicReference<String> id =new AtomicReference<String>();
        AtomicInteger key =new AtomicInteger();
        AtomicBoolean yes = new AtomicBoolean(true);
        idMap.keySet()
                .stream()
                .forEach(k -> {
                    if (yes.get()) {
                        id.set(idMap.get(k));
                        key.set(k);
                        yes.set(false);
                    }
                });
        idMap.remove(key.get());
        return id.get();
    }
}
