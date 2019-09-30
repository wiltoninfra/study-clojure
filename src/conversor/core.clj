(ns conversor.core
(:require [clojure.tools.cli :refer [parse-opts]]
          [clj-http.client :as http-client])
  (:gen-class))


(def opcoes-do-programa
[["-d" "--de moeda base" "moeda base para conversao"
  :default "eur"]
  ["-p" "--para moeda destino"
        "moeda a qual queremos saber o valor"]])

(def chave "d3bf279d7e9e5307743f")

(def api-url
   "https://free.currconv.com/api/v7/convert")

(defn parametrizar-moedas [de para]
    (str de "_" para))

(defn -main
  [& args]
  (let [{:keys [de para]} (:options
                            (parse-opts args opcoes-do-programa))]
  (prn "Cotacao:" (http-client/get api-url
     {:query-params { "q" (parametrizar-moedas de para)
                            "apiKey" chave}}))))

