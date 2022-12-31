package pl.javastart.jpaoptimalization.country;

import java.util.ArrayList;
import java.util.List;

public class CountryWithLanguagesDto {

    private String countryName;
    private List<UsedCountryLanguages> usedCountryLanguages;

    public CountryWithLanguagesDto() {
    }

    public CountryWithLanguagesDto(String countryName, List<CountryWithLanguages> countryWithLanguages) {
        this.countryName = countryName;
        usedCountryLanguages = new ArrayList<>();
        for (CountryWithLanguages c : countryWithLanguages) {
            if (c.getLanguage() != null) {
                UsedCountryLanguages used = new UsedCountryLanguages(c.getLanguage(), c.getPercentage());
                usedCountryLanguages.add(used);
            }
        }
    }

    class UsedCountryLanguages {
        private String language;
        private Double percentage;

        public UsedCountryLanguages() {
        }

        public UsedCountryLanguages(String language, Double percentage) {
            this.language = language;
            this.percentage = percentage;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Double getPercentage() {
            return percentage;
        }

        public void setPercentage(Double percentage) {
            this.percentage = percentage;
        }

        public String toString() {
            return language + " " + percentage + "%";
        }
    }

    public String getCountryName() {
        return countryName;
    }

    public List<UsedCountryLanguages> getUsedCountryLanguages() {
        return usedCountryLanguages;
    }
}
