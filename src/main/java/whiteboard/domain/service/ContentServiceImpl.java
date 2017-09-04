package whiteboard.domain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whiteboard.data.ContentDao;
import whiteboard.domain.model.Announcement;

import javax.transaction.Transactional;
import java.util.List;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    public List<Announcement> findAnnouncementsBySubjectCode(String code) {
        return contentDao.findAnnouncementsBySubjectCode(code);
    }

    @Override
    public void addAnnouncement(Announcement announcement) {
        logger.info("Announcement has saved.");
        contentDao.save(announcement);
    }

    @Override
    public Announcement findAnnouncementById(Long id) {
        return contentDao.findById(id);
    }

    private static final Logger logger = LogManager.getLogger(ContentServiceImpl.class);
}
