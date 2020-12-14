(ns sparkofreason.advent-of-code-2020.day6
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(def input (slurp "resources/day-6.txt"))

(def lines (string/split input #"\n\n"))

(defn group-xf 
  [reducing-fn]
  (map (fn [g]
         (transduce 
          (comp
           (map seq)
           (map set))
          reducing-fn
          (string/split g #"\n")))))

(transduce (comp (group-xf set/union) (map count)) + lines)

(defn intersection
  ([] nil)
  ([s] s)
  ([r s] (if r (set/intersection r s) s)))

(transduce (comp (group-xf intersection) (map count)) + lines)
