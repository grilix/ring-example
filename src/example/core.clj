(ns example.core
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "<html><body><p>"
              "Hola, " (request :name) "!"
              "</p></body></html>")})

(defn wrap-add-info [handler]
  (fn [request]
    (let [name "usuario"
          request (assoc request :name name)]
      (assoc (handler request) :name name))))

(defn -main []
  (jetty/run-jetty
    (wrap-add-info handler)
    {:port 3000}))
