package grpc;

import com.google.protobuf.Empty;
import com.kumuluz.ee.grpc.annotations.GrpcService;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import io.grpc.stub.StreamObserver;
import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.input.MediaInput;
import si.fri.mag.services.MediaKeywordsService;
import si.fri.mag.services.MediaService;
import si.fri.mag.services.ProjectMediaService;

import javax.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class MediaMetadataServiceImpl extends MediaMetadataGrpc.MediaMetadataImplBase {
    MediaService mediaService;
    ProjectMediaService projectMediaService;
    MediaKeywordsService mediaKeywordsService;
    @Override
    public void newMediaMetadata(MediametadataService.CreateNewMediaMetadataRequest request, StreamObserver<MediametadataService.MediaMetadataResponse> responseObserver) {
        MediaInput mediaInput = new MediaInput();
        mediaInput.setName(request.getName());
        mediaInput.setSiteName(request.getSiteName());
        mediaInput.setLength(request.getLength());
        mediaInput.setStatus(request.getStatus());
        mediaInput.setThumbnail(request.getThumbnail());
        mediaInput.setProjectId(request.getProjectId());
        mediaInput.setAwsBucketWholeMedia(request.getAwsBucketWholeMedia());
        mediaInput.setAwsStorageNameWholeMedia(request.getAwsStorageNameWholeMedia());

        mediaService = CDI.current().select(MediaService.class).get();
        MediaDTO mediaDTO = mediaService.addNewMedia(mediaInput);

        responseObserver.onNext(this.buildMediaMetadataResponse(mediaDTO));
        responseObserver.onCompleted();
    }

    @Override
    public void updateMediaMetadata(MediametadataService.UpdateMediaRequest request, StreamObserver<MediametadataService.MediaMetadataResponse> responseObserver) {
        MediaInput mediaInput = new MediaInput();

        mediaInput.setMediaId(request.getMediaId());
        mediaInput.setName(request.getName());
        mediaInput.setSiteName(request.getSiteName());
        mediaInput.setLength(request.getLength());
        mediaInput.setStatus(request.getStatus());
        mediaInput.setThumbnail(request.getThumbnail());
        mediaInput.setProjectId(request.getProjectId());
        mediaInput.setAwsBucketWholeMedia(request.getAwsBucketWholeMedia());
        mediaInput.setAwsStorageNameWholeMedia(request.getAwsStorageNameWholeMedia());
        mediaInput.setCreatedAt(request.getCreatedAt());

        mediaService = CDI.current().select(MediaService.class).get();
        MediaDTO mediaDTO = mediaService.updateMedia(mediaInput.getMediaId(), mediaInput);

        responseObserver.onNext(this.buildMediaMetadataResponse(mediaDTO));
        responseObserver.onCompleted();
    }

    @Override
    public void getMediaMetadata(MediametadataService.GetMediaMetadataRequest request, StreamObserver<MediametadataService.MediaMetadataResponse> responseObserver) {
        mediaService = CDI.current().select(MediaService.class).get();
        MediaDTO mediaDTO = mediaService.getMedia(request.getMediaId());

        responseObserver.onNext(this.buildMediaMetadataResponse(mediaDTO));
        responseObserver.onCompleted();
    }

    @Override
    public void getAllMediaMetadata(Empty request, StreamObserver<MediametadataService.MediaMetadataResponseRepeated> responseObserver) {
        mediaService = CDI.current().select(MediaService.class).get();
        List<MediaDTO> mediaDTOS = mediaService.getAllMedias();

        MediametadataService.MediaMetadataResponseRepeated response = MediametadataService.MediaMetadataResponseRepeated
                .newBuilder()
                .addAllData(this.buildMediaMetadataResponseRepeated(mediaDTOS))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getLiveMedias(Empty request, StreamObserver<MediametadataService.MediaMetadataResponseRepeated> responseObserver) {
        mediaService = CDI.current().select(MediaService.class).get();
        List<MediaDTO> mediaDTOS = mediaService.getLiveMedias();

        MediametadataService.MediaMetadataResponseRepeated response = MediametadataService.MediaMetadataResponseRepeated
                .newBuilder()
                .addAllData(this.buildMediaMetadataResponseRepeated(mediaDTOS))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMediasInProgress(Empty request, StreamObserver<MediametadataService.MediaMetadataResponseRepeated> responseObserver) {
        mediaService = CDI.current().select(MediaService.class).get();
        List<MediaDTO> mediaDTOS = mediaService.getMediasInProgress();

        MediametadataService.MediaMetadataResponseRepeated response = MediametadataService.MediaMetadataResponseRepeated
                .newBuilder()
                .addAllData(this.buildMediaMetadataResponseRepeated(mediaDTOS))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProjectMediasMetadata(MediametadataService.GetProjectMediasRequest request, StreamObserver<MediametadataService.MediaMetadataResponseRepeated> responseObserver) {
        projectMediaService = CDI.current().select(ProjectMediaService.class).get();
        List<MediaDTO> mediaDTOS = projectMediaService.getProjectMedias(request.getProjectId());

        MediametadataService.MediaMetadataResponseRepeated response = MediametadataService.MediaMetadataResponseRepeated
                .newBuilder()
                .addAllData(this.buildMediaMetadataResponseRepeated(mediaDTOS))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getOneProjectMediasMetadata(MediametadataService.GetOneProjectMedia request, StreamObserver<MediametadataService.MediaMetadataResponse> responseObserver) {
        projectMediaService = CDI.current().select(ProjectMediaService.class).get();
        MediaDTO mediaDTO = projectMediaService.getProjectOneMedia(request.getMediaId(), request.getProjectId());

        responseObserver.onNext(this.buildMediaMetadataResponse(mediaDTO));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteMedia(MediametadataService.GetMediaMetadataRequest request, StreamObserver<MediametadataService.StatusResponse1> responseObserver) {
        mediaService = CDI.current().select(MediaService.class).get();
        boolean isDeleted = mediaService.deleteMedia(request.getMediaId());
        if (!isDeleted) {
            responseObserver.onError(new Error("media not deletet: " + isDeleted));
            responseObserver.onCompleted();
        }
        MediametadataService.StatusResponse1 statusResponse = MediametadataService.StatusResponse1.newBuilder()
                .setMessage("Media deleted")
                .setStatus(200)
                .setData(isDeleted)
                .build();
        responseObserver.onNext(statusResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void updateMediaKeywords(MediametadataService.UpdateMediaKeywords request, StreamObserver<MediametadataService.MediaMetadataResponse> responseObserver) {
        mediaKeywordsService = CDI.current().select(MediaKeywordsService.class).get();
        MediaDTO mediaDTO = mediaKeywordsService.updateKeyWords(request.getMediaId(), request.getKeywordsList());

        if (mediaDTO == null) {
            responseObserver.onError(new Throwable("error when updating media keywords"));
            responseObserver.onCompleted();
            return;
        }

        responseObserver.onNext(this.buildMediaMetadataResponse(mediaDTO));
        responseObserver.onCompleted();
    }

    private List<MediametadataService.MediaMetadataResponse> buildMediaMetadataResponseRepeated(List<MediaDTO> mediaDTOS) {

        List<MediametadataService.MediaMetadataResponse> mediaMetadataResponses = new ArrayList<>();

        for (MediaDTO mediaDTO : mediaDTOS) {
            mediaMetadataResponses.add(this.buildMediaMetadataResponse(mediaDTO));
        }

        return mediaMetadataResponses;
    }

    private MediametadataService.MediaMetadataResponse buildMediaMetadataResponse(MediaDTO mediaDTO) {
        return MediametadataService.MediaMetadataResponse.newBuilder()
                .setMediaId(mediaDTO.getMediaId())
                .setName(mediaDTO.getName())
                .setSiteName(mediaDTO.getSiteName())
                .setLength(mediaDTO.getLength())
                .setStatus(mediaDTO.getStatus())
                .setThumbnail(mediaDTO.getThumbnail() == null ? "" : mediaDTO.getThumbnail())
                .setProjectId(mediaDTO.getProjectId() == null ? -1 : mediaDTO.getProjectId())
                .setAwsBucketWholeMedia(mediaDTO.getAwsBucketWholeMedia())
                .setAwsStorageNameWholeMedia(mediaDTO.getAwsStorageNameWholeMedia())
                .setCreatedAt(mediaDTO.getCreatedAt().getTime())
                .setUpdatedAt(mediaDTO.getUpdatedAt().getTime())
                .addAllKeywords(mediaDTO.getKeywords())
                .build();
    }
}
