package com.visualnuts;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainProgram
{
    public static void main(String[] args)
    {    
        print("Primeiro Exercício \n");
        
        PrintNumbers printNumbers = new PrintNumbers();
        
        printNumbers.execute(-20, 8);
        printNumbers.execute(null, -1);
        printNumbers.execute(10000, null);
        
        printNumbers.execute(1, 100);
        printNumbers.execute(100, 1);
        printNumbers.execute(25, 500);
       
        
        print("\nSegundo Exercício\n");

        JSONParser jsonParser = new JSONParser();

        try
        {
            FileReader reader = new FileReader("spokenCountries.json");

            JSONArray countries = (JSONArray) jsonParser.parse(reader);

            extractJson(countries);

        } catch (Exception e)
        {
            print("Erro ao ler o json: " + e.toString());
        }
    }

    @SuppressWarnings("unchecked")
    private static void extractJson(JSONArray countries)
    {
        JSONObject country;
        JSONArray languages;

        Set<String> allLanguagesSpokenSet = new HashSet<String>();
        List<String> languagesSpokenList = new ArrayList<String>();

        String highestCountry = "";
        Integer numberOfHC = 0;
        
        String highestCountryDE = "";
        Integer numberOfHCDE = 0;

        for (Object obj : countries)
        {
            country = (JSONObject) obj;
            languages = (JSONArray) country.get("languages");

            if (numberOfHC < languages.size())
            {
                numberOfHC = languages.size();
                highestCountry = country.get("country").toString();
            }

            print("Country:" + country.get("country"));
            print("Languages: " + String.join(", ", languages));
            print("-------------------------");

            for (Object lang : languages)
            {
                allLanguagesSpokenSet.add(lang.toString());
                languagesSpokenList.add(lang.toString());

                if (lang.toString().equals("de"))
                {
                    if (numberOfHCDE < languages.size())
                    {
                        numberOfHCDE = languages.size();
                        highestCountryDE = country.get("country").toString();
                    }

                }
            }

        }

        Map<String, Long> counts = languagesSpokenList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        long max = Collections.max(counts.values());
        List<String> result = counts.entrySet().stream().filter(e -> e.getValue().longValue() == max).map(Map.Entry::getKey)
                .collect(Collectors.toList());

        print("\nThis World have " + countries.size() + " countries! ");
        print("The country with the most official languages, where they officially speak German (de) is " + highestCountryDE + " with " + numberOfHCDE + " languages!");
        print("The counts of all official languages spoken in the listed countries: " + allLanguagesSpokenSet.size() + " (" + String.join(", ", allLanguagesSpokenSet) + ")");
        print("The country with the highest number of official languages is " + highestCountry + " with " + numberOfHC + " languages!");
        print("The most common official language(s) of all countries: " + String.join(", ", result));
    }

    private static void print(String msg)
    {
        System.out.println(msg);
    }

}
