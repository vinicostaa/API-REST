(ns clojure7.db
  (:use korma.db))

  (defdb db (mysql
            { :classname "com.mysql.jdbc.Driver"
              :subprotocol "mysql"
              :subname "//localhost:32779/clojure7"
              :user "root"
              :password "tcc@2016"}))