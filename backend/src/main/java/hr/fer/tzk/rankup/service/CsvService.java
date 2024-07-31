package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.Participation;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.EventRepository;
import hr.fer.tzk.rankup.repository.MemberRepository;
import hr.fer.tzk.rankup.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CsvService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private EventService eventService;

    public void convertLichessCsvToDb(InputStream csvInputStream, String eventName, int defaultPoints) throws IOException {
        Optional<Section> chessSectionOpt = sectionService.getSectionById(1L);
        if (chessSectionOpt.isEmpty()) {
            throw new IOException("Section not found");
        }
        Section chessSection = chessSectionOpt.get();

        Optional<Event> eventOpt = eventService.findByName(eventName);
        if (eventOpt.isEmpty()) {
            throw new IOException("Event not found");
        }
        Event event = eventOpt.get();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvInputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Integer rank = Integer.valueOf(data[0]);
                String lichessUsername = data[2];
                Integer points = Integer.valueOf(data[4]);


                Member member = memberRepository.findByLichessUsername(lichessUsername).orElse(null);
                if (member != null) {
                    Participation participation = new Participation();
                    participation.setIdMember(member.getId());
                    participation.setIdEvent(event.getId());
                    participation.setAddPoints((long) defaultPoints);

                    participationRepository.save(participation);
                }
            }
        }
    }
}
