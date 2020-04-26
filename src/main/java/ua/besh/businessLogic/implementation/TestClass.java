package ua.besh.businessLogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.besh.dataAccess.domain.*;
import ua.besh.dataAccess.implementation.CsvData;
import ua.besh.dataAccess.interfaces.ICsvReader;
import ua.besh.dataAccess.interfaces.repos.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TestClass {

    @Autowired
    private ISongRepo songRepo;

    @Autowired
    private IAuthorRepo authorRepo;

    @Autowired
    private IAlbumRepo albumRepo;

    @Autowired
    private ILibraryRepo libraryRepo;

    @Autowired
    private ICardRepo cardRepo;

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private IPlaylistRepo playlistRepo;

    @GetMapping("/")
    public void createSong() {

        ICsvReader csvData = new CsvData();
        String userData = csvData.getUserRecords();
        String authorData = csvData.getAuthorRecords();
        String albumData = csvData.getAlbumRecords();
        String cardData = csvData.getCardRecords();
        String libraryData = csvData.getLibraryRecords();
        String playlistData = csvData.getPlaylistRecords();
        String songData = csvData.getSongRecords();

        String authorRecordsToSplit[] = authorData.split("\\n");
        List<String[]> authorsList = new LinkedList<>();
        List<Author> authorsToInsert = new LinkedList<>();

        for (String record: authorRecordsToSplit) {
            authorsList.add(record.split(","));
        }

        for (String[] userRecord: authorsList) {

            Author author = new Author(userRecord[0]);
            authorsToInsert.add(author);
        }

        authorRepo.saveAll(authorsToInsert);

        String userRecordsToSplit[] = userData.split("\\n");
        List<String[]> userList = new LinkedList<>();

        for (String record: userRecordsToSplit) {
            userList.add(record.split(","));
        }

        List<User> userToInsert = new ArrayList<>();

        for (String[] userRecord: userList) {
            User usr = new User(userRecord[0], userRecord[1], userRecord[2]);
            userToInsert.add(usr);
        }

        userRepo.saveAll(userToInsert);

        String albumRecordsToSplit[] = albumData.split("\\n");
        List<String[]> albumList = new LinkedList<>();

        for (String record: albumRecordsToSplit) {
            albumList.add(record.split(","));
        }

        List<Album> albumToInsert = new LinkedList<>();

        for (String[] albumRecord: albumList) {
            Author author = authorRepo.getAuthorById(Long.valueOf(albumRecord[1]));
            Album album = new Album(albumRecord[0], author);
            albumToInsert.add(album);
        }

        albumRepo.saveAll(albumToInsert);

        String cardTosplit[] = cardData.split("\\n");
        List<String[]> cardList = new LinkedList<>();

        for (String record: cardTosplit) {
            cardList.add(record.split(","));
        }

        List<Card> cardToInsert = new LinkedList<>();
        int i = 0;
        for (String[] cardRecord: cardList) {

            Card card = new Card(Long.valueOf(cardRecord[0]), cardRecord[1], Integer.parseInt(cardRecord[2]),
                    cardRecord[3], userToInsert.get(i));
            cardToInsert.add(card);
            i++;
        }

        cardRepo.saveAll(cardToInsert);

        String libraryToSplit[] = libraryData.split("\\n");
        List<String[]> libraryList = new LinkedList<>();

        for (String record: libraryToSplit) {
            libraryList.add(record.split(","));
        }

        List<Library> libraryToInsert = new LinkedList<>();

        for (String[] libraryRecord: libraryList) {
            User user = userRepo.findUserById(Long.valueOf(libraryRecord[0]));
            Library library = new Library(user);
            libraryToInsert.add(library);
        }

        libraryRepo.saveAll(libraryToInsert);

        String playlistToSplit[] = playlistData.split("\\n");
        List<String[]> playlistList = new LinkedList<>();

        for (String record: playlistToSplit) {
            playlistList.add(record.split(","));
        }

        List<Playlist> playlistToInsert = new LinkedList<>();

        for (String[] playlistRecord: playlistList) {
            Playlist playlist = new Playlist(playlistRecord[0]);
            playlistToInsert.add(playlist);
        }

        playlistRepo.saveAll(playlistToInsert);

        String songToSplit[] = songData.split("\\n");
        List<String[]> songList = new LinkedList<>();

        for (String record: songToSplit) {
            songList.add(record.split(","));
        }

        List<Song> songListToInsert = new LinkedList<>();

        for (String[] songRecord: songList) {
            Song song = new Song(songRecord[0], songRecord[1], Integer.parseInt(songRecord[2]));
            songListToInsert.add(song);
        }

        songRepo.saveAll(songListToInsert);

    }
}
