# language: ru
  Функция: Удаление плейлиста

    @success
    Сценарий: Успешное удаление плейлиста
      Дано В плейлистах пользователя есть плейлист с названием 'my_playlist'
      Когда Удалить плейлист с идентификатором 9463544002
      Тогда Плейлист с названием 'my_playlist' отсутствует в списке плейлистов пользователя "4571342102"

    @fail
    Сценарий: Искомого плейлиста не существует
