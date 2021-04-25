import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.wall.WallGetFilter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException, ClientException, ApiException {

        TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
        vktelegrambot bot = new vktelegrambot();
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // peter samokhin
        /* User usr = new User(147526040, "access_token");

        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Что-то скучновато буковки читать. Картинку кинь лучше.")
                        .send()
        );
            */

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(7832400, "Jd9iFHB2vOBUARUxWNUG", "http://oauth.vk.com/blank.html", "ad90261093253cc56fe246e990433744d1d134f8ab8f95d974f0f0de542aed6b2026f0be4e35f62023f63")
                .execute();






        // хз что это -_-
            UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        GetResponse getResponse = vk.wall().get(actor)
                .ownerId(1)
                .count(100)
                .offset(5)
                .filter(WallGetFilter.valueOf("owner"))
                .execute();



       // bot.getJsonResponse();




        // это для групп вк

        /* int groupId = 151083290;
        String accessToken = "abcdef123456...";
        HttpClient vkHttpClient = new VkOkHttpClient();

        VkApiClient client = new VkApiClient(groupId, accessToken, VkApiClient.Type.Community, new VkSettings(vkHttpClient));

        client.onMessage(event -> {
            new Message()
                    .peerId(event.getMessage().getPeerId())
                    .text("Hello, world!")
                    .sendFrom(client)
                    .execute();
        });

        client.startLongPolling(); */

    }

}
