package si.fri.mag.services;

import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.converters.MediaConverter;
import si.fri.mag.entities.MediaEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class MediaService {
    @Inject
    private EntityManager em;

    @Inject
    private MediaConverter mediaConverter;

    public List<MediaDTO> getAllMedias() {
        Query q = em.createNamedQuery("getAllMedias");

        List<MediaEntity> mediaEntities = (List<MediaEntity>)q.getResultList();

        List<MediaDTO> mediaDTOS = new ArrayList<MediaDTO>();
        for (MediaEntity mediaEntity : mediaEntities) {
            mediaDTOS.add(this.mediaConverter.toDTO(mediaEntity));
        }

        return mediaDTOS;
    }
}
