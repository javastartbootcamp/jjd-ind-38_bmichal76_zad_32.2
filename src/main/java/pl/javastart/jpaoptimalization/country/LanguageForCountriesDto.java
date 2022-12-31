package pl.javastart.jpaoptimalization.country;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageForCountriesDto {
    private String language;
    private List<String> countries;

    public LanguageForCountriesDto() {
    }

    public LanguageForCountriesDto(String language, List<LanguageForCountries> countries) {
        this.language = language;
        this.countries = countries.stream().map(LanguageForCountries::getCountryName).collect(Collectors.toList());
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getCountries() {
        return countries;
    }
}
