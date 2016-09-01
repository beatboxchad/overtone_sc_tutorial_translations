(ns sc-tutorial-translations.tutorial3
  [:require [overtone.live]])

(defsynth pulsetest "A "
  [ampHz 4
   fund 40
   maxPartial 4
   width 0.5]

  (let [amp1 (* (lf-pulse:kr ampHz 0 0.12) 0.75)
        amp2 (* (lf-pulse:kr ampHz 0.5 0.12) 0.75)
        freq1 (* (mul-add (lf-pulse:kr 8) 1 1) (round (lin-exp:kr :in (lf-noise0:kr 4) :dstlo fund :dsthi (* fund maxPartial)) fund))
        freq2 (* (mul-add (lf-pulse:kr 6) 1 1) (round (lin-exp:kr :in (lf-noise0:kr 4) :dstlo fund :dsthi (* fund maxPartial)) fund))
        raw2 
        sig1 (free-verb:ar (* amp1 (pulse:ar freq1 width)) 0.7 0.8 0.25)
        sig2 (free-verb:ar (* amp1 (pulse:ar freq2 width)) 0.7 0.8 0.25)
        ]
    (do
      (out 0 sig1)
      (out 1 sig2))
    )
  )

(stop)
(def active (pulsetest))

(ctl active :ampHz 4.23)
(ctl active :fund 30)
(ctl active :maxPartial 12)
(ctl active :width 0.5)

(stop)
