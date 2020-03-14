package si.fri.mag.services;

import si.fri.mag.entities.MediaEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
public class MediaService {
    @Inject
    // private EntityManager em;

    public void getAllMedias() {
        System.out.println("Get all medias");
        // MediaEntity media = em.find(MediaEntity.class, 1);
        // System.out.println(media.getName());
    }
}
