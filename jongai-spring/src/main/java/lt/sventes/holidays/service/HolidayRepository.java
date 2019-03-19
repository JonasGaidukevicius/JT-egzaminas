package lt.sventes.holidays.service;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.sventes.holidays.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	
	Holiday findHolidayByCode(String code);
	void deleteHolidayByCode(String code);
	//Paieškos pagal title nenaudosiu, kai reikia surasti šventę, kurią reikia atnaujinti
	Holiday findHolidayByTitle(String title);
	//Trynimo pagal title irgi nedarysiu
	void deleteHolidayByTitle(String title);

	
	
}
