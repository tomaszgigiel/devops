(ns pl.tomaszgigiel.devops.common
  (:require [clojure.java.io :as io])
  (:require [clojure.set :as set])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:gen-class))

(defn flatten-one-level [coll] (apply concat coll))
