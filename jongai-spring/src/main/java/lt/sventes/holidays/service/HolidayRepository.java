package lt.sventes.holidays.service;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.sventes.holidays.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	
	Holiday findHolidayByCode(String code);
	void deleteHolidayByCode(String code);

}
