(ns sparkofreason.advent-of-code-2020.day2
  (:require [clojure.string :as string]))

(def input "1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc")

(defn parse-input
  [input]
  (->> (string/split input #"\n")
    (map (fn [%]
           (let [[r c p] (string/split % #" ")
                 [min max] (map #(Long/parseLong %) (string/split r #"-"))
                 c (first c)]
             {:min min :max max :char c :password p}
             )))))

(defn valid?
  [{:keys [min max char password]}]
  (<= min (->> password (filter #(= char %)) count) max))

(defn valid-passwords
  [input]
  (->> input
    parse-input
    (filter valid?)))

(-> "resources/day-2.txt"
    slurp
    valid-passwords
    count)
