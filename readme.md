# Testing Deezer API
Тестирование фукнционала API музыкального сервиса Deezer. Основные тесты направлены на работу с плейлистами: 
создание и удаление плейлистов, добавление и удаление треков из плейлста, добавление чужих плейлистов в любимые
и удалиние их оттуда. Также тестируется возможность отслеживания других пользователей и поиск исполнителей.
### Реализованные теги:
* @playlist Тесты функционала плейлистов
* @follow Тесты функционала отслеживания пользователей
* @search Тест функционала поиска
* @album  Тест функционала альбомов
* @podcast Тест функционала подкастов (@notworking Видимо пока возможность полностью не реализована)
* @dbtest Тест проверкой базы данных (Отключены в раннере, настройки в docker)
  * ##### Данные для входа pgadmin
    * Email: admin@admin.ru
    * Password: 123
  * ##### Server:
    * Host: postgresDb
    * Username: postgres
    * Password: 123

### Структура таблиц базы данных для @dbtest
1. Название таблицы: playlists
   1. Столбец 1: playlist_id. Тип: bigint. Primary key
   2. Столбец 2: title. Тип: text
2. Название таблицы: tracks
    1. Столбец 1: track_id. Тип: bigint. Primary key
    2. Столбец 2: name. Тип: text
3. Название таблицы: playlist_tracks
    1. Столбец 1: playlist_id. Тип: bigint. Primary key
    2. Столбец 2: track_id. Тип: bigint. Primary key
    3. Foreing keys: playlist_id reference playlists (playlist_id), track_id reference tracks(track_id)