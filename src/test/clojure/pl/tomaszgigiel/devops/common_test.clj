(ns pl.tomaszgigiel.devops.common-test
  (:require [clojure.test :as tst])
  (:require [pl.tomaszgigiel.devops.common :as common])
  (:require [pl.tomaszgigiel.devops.test-config :as test-config]))

(tst/use-fixtures :once test-config/once-fixture)
(tst/use-fixtures :each test-config/each-fixture)
