package grpc;

import com.kumuluz.ee.grpc.annotations.GrpcService;
import io.grpc.stub.StreamObserver;
import si.fri.mag.DTO.MediaDTO;
import si.fri.mag.input.MediaInput;
import si.fri.mag.services.MediaService;

import javax.enterprise.inject.spi.CDI;

@GrpcService
public class MediaMetadataServiceImpl extends MediaMetadataGrpc.MediaMetadataImplBase {
    MediaService mediaService;
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
