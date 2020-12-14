(ns sparkofreason.advent-of-code-2020.day3
  (:require [clojure.string :as string]))

(def input (slurp "resources/day-3.txt"))

(def trees (string/split input #"\n"))

(def width (-> trees first count))

(defn tree?
  [right width n line]
  (let [i (mod (* n right) width)]
     (= \# (nth line i))))

(defn count-trees
  [right down width trees]
  (->> trees
    (take-nth down)
    (map (partial tree? right width) (range))
    (filter identity)
    count))

(count-trees 3 1 width trees)

(def slopes [[1 1] [3 1] [5 1] [7 1] [1 2]])

(apply * (map (fn [[right down]] (count-trees right down width trees)) slopes))
