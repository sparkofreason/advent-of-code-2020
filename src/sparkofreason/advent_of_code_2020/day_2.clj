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

(->> "resources/day-2.txt"
    slurp
    parse-input
    (filter valid?)
    count)

(defn valid2?
  [{:keys [min max char password]}]
  (let [l (count password)
        c1 (and (<= min l) (= char (nth password (dec min))))
        c2 (and (<= max l) (= char (nth password (dec max))))]
    (and (or c1 c2) (or (not c1) (not c2)))))

(->> "resources/day-2.txt"
    slurp
    parse-input
    (filter valid2?)
    count)

