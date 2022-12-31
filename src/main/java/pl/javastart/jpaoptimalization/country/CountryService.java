package pl.javastart.jpaoptimalization.country;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public List<CountryWithBiggestCity> findAllCountryWithBiggestCity() {
        return countryRepository.findAllCountryWithBiggestCity();
    }

    public List<CountryWithLanguagesDto> findAllCountriesWithLanguages() {
        List<CountryWithLanguages> countryWithLanguages = countryRepository.findAllCountriesWithLanguages();
        Map<String, List<CountryWithLanguages>> map = countryWithLanguages.stream()
                .collect(Collectors.groupingBy(CountryWithLanguages::getCountryName));

        List<CountryWithLanguagesDto> countryWithLanguagesDto = new ArrayList<>();
        String country = "";
        for (CountryWithLanguages c : countryWithLanguages) {
            if (!country.equals(c.getCountryName())) {
                country = c.getCountryName();
                countryWithLanguagesDto.add(new CountryWithLanguagesDto(country, map.get(country)));
            }
        }
        return countryWithLanguagesDto;
    }

    public List<LanguageForCountriesDto> findLanguagesUsedInCountries() {
        List<LanguageForCountries> languageForCountries = countryRepository.findLanguagesUsedInCountries();
        var map = languageForCountries.stream()
                .collect(Collectors.groupingBy(LanguageForCountries::getLanguage));

        List<LanguageForCountriesDto> languageForCountriesDto = new ArrayList<>();
        String language = "";
        for (LanguageForCountries l : languageForCountries) {
            if (!language.equals(l.getLanguage())) {
                language = l.getLanguage();
                languageForCountriesDto.add(new LanguageForCountriesDto(language, map.get(language)));
            }
        }

        return languageForCountriesDto;
    }
}
