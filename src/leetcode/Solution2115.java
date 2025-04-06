package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Array, Hash Table, String, Graph, Topological Sort
public class Solution2115 {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> result = new ArrayList<>(recipes.length);
        Set<String> can = new HashSet<>(Arrays.asList(supplies));
        Map<String, List<Integer>> cant = new HashMap<>();

        for (int i = 0; i < recipes.length; i++) {
            List<String> gredients = ingredients.get(i);
            Iterator<String> iterator = gredients.iterator();
            while (iterator.hasNext()) {
                String dient = iterator.next();
                if (can.contains(dient)) {
                    iterator.remove();
                } else {
                    if (cant.containsKey(dient)) {
                        cant.get(dient).add(i);
                    } else {
                        List<Integer> dients = new ArrayList<>();
                        dients.add(i);
                        cant.put(dient, dients);
                    }
                }
            }
            if (gredients.isEmpty()) {
                remove(can, result, cant, recipes[i], recipes, ingredients);
            }
        }

        return result;
    }

    public void remove(Set<String> can, List<String> result, Map<String, List<Integer>> cant, String recipe, String[] recipes, List<List<String>> ingredients) {
        can.add(recipe);
        result.add(recipe);
        if (cant.containsKey(recipe)) {
            for (Integer index : cant.get(recipe)) {
                ingredients.get(index).remove(recipe);
                if (ingredients.get(index).isEmpty()) {
                    // recursion
                    remove(can, result, cant, recipes[index], recipes, ingredients);
                }
            }
        }
    }

    public static void main(String... args) {
        Solution2115 solution = new Solution2115();
        String[] recipes = new String[] {"bread"};
        List<List<String>> ingredients = new ArrayList<>();
        String[] supplies = new String[] {"yeast", "flour", "corn"};
        List<String> bread = new ArrayList<>();
        bread.add("yeast");
        bread.add("flour");
        ingredients.add(bread);
        System.out.println(solution.findAllRecipes(recipes, ingredients, supplies));
    }
}
