package br.com.devbean.awslambda;

import br.com.devbean.awslambda.dto.LambdaPayload;
import br.com.devbean.awslambda.dto.Record;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.InputStream;
import java.io.OutputStream;

public class AppHandler implements RequestStreamHandler {

//    private LambdaLogger logger;

    @Override
    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream,
                              Context context) {

       log(context, "START => Initiating Lambda Function");

        final var payload = LambdaPayload.of(inputStream, context.getLogger());
        final var records = payload.getRecords();

        log(context, String.format("PROCESSING => Records size %d found", records.size()));

        for(Record record : records) {
            final var notificationDataDTO = record.getNotificationRequestDTO();
            log(context,
                    String.format("Input => Message ID: %s | Subject: %s",
                            record.getMessageId(),
                            notificationDataDTO.getSubject()
                    )
            );
        }
        log(context, "END => Lambda Function");
    }

    private void log(Context context, String message) {
        context.getLogger().log(String.format("[%s] %s", context.getAwsRequestId(), message));
    }

}


