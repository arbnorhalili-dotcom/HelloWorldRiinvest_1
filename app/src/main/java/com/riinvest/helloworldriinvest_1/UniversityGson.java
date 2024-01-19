package com.riinvest.helloworldriinvest_1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UniversityGson {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alpha_two_code")
    @Expose
    private String alphaTwoCode;
    @SerializedName("state-province")
    @Expose
    private Object stateProvince;
    @SerializedName("domains")
    @Expose
    private List<String> domains;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("web_pages")
    @Expose
    private List<String> webPages;

    /**
     * No args constructor for use in serialization
     *
     */
    public UniversityGson() {
    }

    /**
     *
     * @param country
     * @param webPages
     * @param name
     * @param stateProvince
     * @param domains
     * @param alphaTwoCode
     */
    public UniversityGson(String name, String alphaTwoCode, Object stateProvince, List<String> domains, String country, List<String> webPages) {
        super();
        this.name = name;
        this.alphaTwoCode = alphaTwoCode;
        this.stateProvince = stateProvince;
        this.domains = domains;
        this.country = country;
        this.webPages = webPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        this.alphaTwoCode = alphaTwoCode;
    }

    public Object getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(Object stateProvince) {
        this.stateProvince = stateProvince;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<String> webPages) {
        this.webPages = webPages;
    }

}