(ns sc-tutorial-translations.tutorial3
  [:require [overtone.core :refer :all]])

(connect-external-server)

(defsynth pulsetest "A synth based on the example synth in Eli Fieldsteel's SuperCollider tutorial #3"
  [ampHz 4
   fund 40
   maxPartial 4
   width 0.5]

  (let [amp1 (* 0.75 (lf-pulse:kr ampHz 0   0.12))
        amp2 (* 0.75 (lf-pulse:kr ampHz 0.5 0.12))

        freq1 (round 
                ; http://doc.sccode.org/Classes/UGen.html#-exprange explains
                ; that it uses a LinExp ugen to do the work. Here, we do the
                ; same manually 
                (lin-exp:kr :in    (lf-noise0:kr 4) 
                            :dstlo fund 
                            :dsthi (* fund maxPartial)) fund)
        freq2 (round 
                ; see above
                (lin-exp:kr :in    (lf-noise0:kr 4) 
                            :dstlo fund 
                            :dsthi (* fund maxPartial)) fund)

        freq1 (* (+ 1 (lf-pulse:kr 8)) freq1)
        freq2 (* (+ 1 (lf-pulse:kr 6)) freq2)

        sig1  (* amp1 (pulse:ar freq1 width))
        sig2  (* amp2 (pulse:ar freq2 width))

        sig1  (free-verb:ar sig1 0.5 0.8 0.25)
        sig2  (free-verb:ar sig2 0.5 0.8 0.25)]
    (do
      (out 0 sig1)
      (out 1 sig2))
    )
  )

(stop)
(def test_synth (pulsetest))

; not true to the tutorial here, just having a little fun
(ctl test_synth :ampHz 5)
(ctl test_synth :fund 242)
(ctl test_synth :maxPartial 13.4)
(ctl test_synth :width 1.754832)

(stop)
