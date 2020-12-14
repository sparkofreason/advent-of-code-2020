(ns sparkofreason.advent-of-code-2020.day4-1
  (:require [clojure.string :as string]
            [clojure.spec.alpha :as s]))

(def input (slurp "resources/day-4.txt"))

(def lines (string/split input #"\n\n"))

(def passports
  (->> lines
    (map #(string/split % #"\s"))
    (map (fn [line]
           (->> line
             (map #(string/split % #":"))
             (map (fn [[k v]] [(keyword k) v]))
             (into {}))))))

(s/def ::passport1 (s/keys :req-un [:d4/byr :d4/iyr :d4/eyr :d4/hgt :d4/hcl :d4/ecl :d4/pid]
                         :opt-un [:d4/cid]))

(->> passports
     (filter #(s/valid? ::passport1 %))
     count)

