# git-demo-1.0
В Дженкинс курсе 5 лабы появилась почта kurnosenko_61@mail.ru (соответственно и новый акк), потому что я не тот рут в первом аккаунте выбрала, пришлось создать ещё один. Так что всё мое, отвечаю.

11-12 лаба
Запуск тестов осуществляется из командной строки. 
Сначала перемещаемся в директорию с pom файлом, например:
cd /d "D:\Университет\Тестирование\testing_lab11-12_framework"
Затем запускаем все тесты одной командой:
mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\main\resources\testng-all.xml clean test
