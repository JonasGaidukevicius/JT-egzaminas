package lt.sventes.holidays.service;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.sventes.holidays.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	
	Holiday findHolidayByCode(String code);
	void deleteHolidayByCode(String code);
	boolean existsByTitle(String title);

	// Savo querių kūrimas
	// @Query("select h from holiday h where h.code = ?1")
	// Holiday findByCode(String code);

	// Anotacija @Modifying reikalinga, jeigu su queriu atlieku duomenų keitimo ar
	// trynimo operaciją
	// @Modifying
	// @Query("update holiday h set h.description = ?1 where h.code = ?2")
	// void setFixedDescriptionFor(String decription, String code);

}
