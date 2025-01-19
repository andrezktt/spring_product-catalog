package com.andrezktt.product_catalog.utils;

import com.andrezktt.product_catalog.entities.Product;
import com.andrezktt.product_catalog.projections.IdProjection;
import com.andrezktt.product_catalog.projections.ProductProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static <ID> List<? extends IdProjection<ID>> replace(List<? extends IdProjection<ID>> ordered, List<? extends IdProjection<ID>> unordered) {
        Map<ID, IdProjection<ID>> map = new HashMap<>();
        unordered.forEach(projection -> map.put(projection.getId(), projection));

        List<IdProjection<ID>> result = new ArrayList<>();
        ordered.forEach(projection -> result.add(map.get(projection.getId())));

        return result;
    }
}
