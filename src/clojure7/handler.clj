(ns clojure7.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure7.post.post :as post]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.util.response :refer [response created redirect]]
            [ring.util.request :refer [request-url]]))

(defroutes all-routes
  (GET "/test" []
    (redirect "localhost:3000/posts" 301))
  (GET "/posts" []
    (response (post/find-all)))
  (POST "/posts" req
    (let [name (get-in req [:body "name"])
          category (get-in req [:body "category"])]
          (created (request-url req) (post/create name category))))
  (GET "/posts/:id" [id]
    (response (post/find-by-id id)))
  (PUT "/posts/:id" req
    (let [id (read-string (get-in req [:params :id]))
          name (get-in req [:body "name"])
          category (get-in req [:body "category"])]
          (post/update-by-id id name category)
          (response (post/find-by-id id))))
  (DELETE "/posts/:id" [id]
    (post/delete-by-id id)
    (response {:id id :msg "Deleted post"}))
  (route/not-found "Not Found"))

(def app
  (-> all-routes
      wrap-json-response
      wrap-json-body))