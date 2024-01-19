package com.riinvest.helloworldriinvest_1;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String Name;
    private String AlphaTwoCode;
    private String Country;
    private List<String> Domains = new ArrayList<>();
    private List<String> WebPages = new ArrayList<>();

    public University(String name, String alphaTwoCode, String country,
                      JSONArray domains, JSONArray webPages) {
        Name = name;
        AlphaTwoCode = alphaTwoCode;
        Country = country;
        for(int i=0; i<domains.length();i++)
        {
            try {
                Domains.add(domains.getString(i));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        for(int i=0;i<webPages.length();i++)
        {
            try {
                WebPages.add(webPages.getString(i));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlphaTwoCode() {
        return AlphaTwoCode;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        AlphaTwoCode = alphaTwoCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public List<String> getDomains() {
        return Domains;
    }

    public void setDomains(List<String> domains) {
        Domains = domains;
    }

    public List<String> getWebPages() {
        return WebPages;
    }

    public void setWebPages(List<String> webPages) {
        WebPages = webPages;
    }
}
