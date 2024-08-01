package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.Participation;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.EventRepository;
import hr.fer.tzk.rankup.repository.MemberRepository;
import hr.fer.tzk.rankup.repository.ParticipationRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExcelUploadService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private EventService eventService;

    public void convertExcelToDb(MultipartFile file, String eventName, int defaultPoints) throws IOException {
        Optional<Section> sectionOpt = sectionService.getSectionById(1L);
        if (sectionOpt.isEmpty()) {
            throw new IOException("Section not found");
        }
        Section section = sectionOpt.get();

        Event event = eventService.findByName(eventName)
                .orElseGet(() -> {
                    Event newEvent = new Event();
                    newEvent.setName(eventName);
                    newEvent.setDefaultPoints(defaultPoints);
                    newEvent.setDate(LocalDate.now());
                    newEvent.setDescription("Excel Upload Event");
                    newEvent.setIdSection(section.getIdSection());
                    return eventRepository.save(newEvent);
                });

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell jmbagCell = row.getCell(0);
                Cell pointsCell = row.getCell(1);

                if (jmbagCell != null && jmbagCell.getCellType() == CellType.STRING && pointsCell != null && pointsCell.getCellType() == CellType.NUMERIC) {
                    String jmbag = jmbagCell.getStringCellValue();
                    int points = pointsCell.getNumericCellValue();

                    Member member = memberRepository.findByJmbag(jmbag).orElse(null);
                    if (member != null) {
                        Participation participation = new Participation();
                        participation.setIdMember(member.getIdMember());
                        participation.setIdEvent(event.getIdEvent());
                        participation.setAddPoints((int) points + defaultPoints);

                        participationRepository.save(participation);
                    }
                }
            }
        }
    }
}