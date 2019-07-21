(ns clojure7.post.post
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [clojure7.db :refer :all]))

(defentity post)

(defn find-all []
  (select post))

(defn find-by-id [id]
    (select post
      (where {:id id})
      (limit 1)))

(defn create [name category]
  (insert post
    (values {:name name :category category})))

(defn update-by-id [id name category]
  (update post
    (set-fields {:name name :category category})
    (where {:id id})))

(defn delete-by-id [id]
  (delete post
    (where {:id id})))