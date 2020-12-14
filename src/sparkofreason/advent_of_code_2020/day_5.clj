(ns sparkofreason.advent-of-code-2020.day5
  (:require [clojure.string :as string]))

(def input (slurp "resources/day-5.txt"))
(def seats (string/split input #"\n"))

(defn binary-partition->number
  [partition-string char->digit]
  (read-string
   (apply str "2r" (map char->digit partition-string))))

(defn seat-id
  [seat]
  (let [row (binary-partition->number (subs seat 0 7) {\F \0 \B \1})
        col (binary-partition->number (subs seat 7) {\R \1 \L \0})]
    (+ (* row 8) col)))

(def seat-ids (vec (sort (map seat-id seats))))

(reduce max seat-ids)

(def index-of-skipped
  (->> seat-ids
     sort
     (partition 2 1)
     (mapv #(apply - %))
     (#(.indexOf % -2))))

(inc (seat-ids index-of-skipped))



