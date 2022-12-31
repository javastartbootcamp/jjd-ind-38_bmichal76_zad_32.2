package pl.javastart.jpaoptimalization.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT c.name as countryName , ct.name as cityName, ct.population as population " +
            "FROM Country c LEFT OUTER JOIN c.cities ct " +
            "WHERE ct.population = (SELECT max(ct2.population) " +
            "FROM City ct2 WHERE ct2.country= c) or ct.population is null " +
            "ORDER BY c.name")
    List<CountryWithBiggestCity> findAllCountryWithBiggestCity();

    @Query(value = "SELECT c.name  as countryName, l.language as language, l.percentage as percentage " +
            "FROM Country c LEFT OUTER JOIN c.languages l " +
            "ORDER BY c.name , l.percentage DESC ")
    List<CountryWithLanguages> findAllCountriesWithLanguages();

    @Query(value = "SELECT l.language as language , c.name as countryName " +
            "FROM Country c JOIN c.languages l ORDER BY l.language , c.name")
    List<LanguageForCountries> findLanguagesUsedInCountries();
}
