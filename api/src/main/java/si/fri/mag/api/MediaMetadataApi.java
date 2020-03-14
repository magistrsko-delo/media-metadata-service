package si.fri.mag.api;

import si.fri.mag.api.controllers.RootController;
import si.fri.mag.api.controllers.v1.MediaMetadataController;
import si.fri.mag.api.mappers.EntityNotFoundMapper;
import si.fri.mag.api.mappers.ForbiddenExceptionMapper;
import si.fri.mag.api.mappers.NotFoundExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MediaMetadataApi extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(EntityNotFoundMapper.class);
        resources.add(ForbiddenExceptionMapper.class);
        resources.add(NotFoundExceptionMapper.class);
        resources.add(MediaMetadataController.class);
        resources.add(RootController.class);
        return resources;
    }
}
