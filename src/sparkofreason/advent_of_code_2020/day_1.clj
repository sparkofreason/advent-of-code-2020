(ns sparkofreason.advent-of-code-2020.day1
  (:require [clojure.string :as string]))

(def expenses (->> "resources/day-1-1.txt"
                   slurp
                   (#(string/split % #"\n"))
                   (map #(Long/parseLong %))
                   set))

(def total 2020)

(defn addends
  [total expenses]
  (let [a (->> expenses
               (map #(- total %))
               (filter expenses)
               first)]
    (when a
      [a (- total a)])))

(apply * (addends total expenses))

(def diffs1 (->> expenses
                 (map (fn [e] [e (- total e)]))
                 (into {})))

(defn addends3
  [total expenses]
  (let [d (map #(- total %) expenses)
        a (->> d
            (map #(addends % expenses))
            (filter some?)
            first)]
    (conj a (- total (apply + a)))))

(apply * (addends3 total expenses))
