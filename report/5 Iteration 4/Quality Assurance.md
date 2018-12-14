# Quality Assurance
I dette afsnit vil der blive gennemgået hvordan gruppen håndterede kvalitetsikring og hvilke overvejelser der blev gjort i forbindelse med kvalitetsikringen. Med kvalitetsikring henvises der til kvalitet af koden, hovedsageligt med fokus på korrekthed. Altså hvorvidt koden udformet af projektgruppen er korrekt i forhold til de ønskede mål.

## Korrekthed
Korrekthed kan betyde mange ting, derfor ønskes det at specificer præcist hvad der menes når gruppen bruger ordet korrekt. Der referes til at hver sektion af koden har et spæcifikt formål som er udtænkt af gruppen for at opnå en specifikt effekt.

Derfor vil det at sikre korrektheden bero sig på at sikre at koden går de aktioner som er nødvendige for at opnå den tiltænkte effekt. Så et eksempel kunne være, at sikre at når gruppen kalder train-metoden på ANN-klassen at samples og labels bliver konverteret til de rigtige data strukturer som skal bruges i det valgte library til ANN og derfor bliver givet videre til library koden og librariet bliver bedt om at træne. Det er ikke relevant for gruppen at kontroller om det bagved liggende kode agerer som det skal. Da koden der udfører denne opgave ikke tilhører gruppen, samtidigt med at gruppen havde et ønske om at holde testen uafhængig af valg omkring hvilke træningsmetoder og aktiveringsmetoder benyttet. På baggrund af dette vurderede gruppen det irrelevant at teste tredje parts kode.

## Fuzzy input, Fuzzy output
En af de store problemstilling omkring at teste koden i dette projekt er at meget lidt af koden er forudsigeligt, forstået på den måde at selv om vi har en test der siger den kan genkende røde cirkler i billeder, betyder det ikke at koden faktisk altid vil kunne genkende røde cirkler, da der er forudsætninger vi ikke kan kontroller så som belysning. Foruden dette kan der heller ikke garanteres at midten at den fundende cirkle altid vil være den helt eksakte midte af cirklen på billedet. 

Det betyder at vores tests ikke kan garanter at fordi testen er successfull så vil enheden altid være successfull i den opgave. Testen specificer bare at givet optimale omstændigheder er enheden i stand til ogpaven, hvilket giver den sikkerhed at der ikke er lavet fejl i koden i forhold til integrationen imellem de forskellige komponenter.

## Mockito
Gruppen benyttede et framework kaldet `Mockito` til at mocke med. `Mockito` beskriver sig selv som et Mocking Framework, men anerkender at der lidt uenigheder omkring ordvalget, da det rent teknisk mere er et `Test Spy Framework`. Frameworket tillader at spionerer på en klasse og verificer metode kald og parameter angivelser som man gender det fra mocks og stubs. 

At benytte dette framework gjorde det muligt for gruppen at abstraherer implementation kode fra tredje parts libraries ud af testen. Dermed tillade at isolerer testen til kun at arbejde på den klasse som er subjektet for testen.

## Testing the Neural Network
For at teste det neurale netværk valgte gruppen at abstrahere selve implementation væk, målet med testende var ikke at teste OpenCVs implementation af det neurale netværk, men derimod teste at gruppen benyttede OpenCvs implementation korrekt i forhold til de ønskede effekter.

I projektet er eksisterer der en class ved navn `ANN` som er gruppens adapter til OpenCVs implementation a neural netværk. I testen bliver der lavet en `spy` på OpenCVs implementation og den bliver injected ind i adapter klassen. Dette tillader gruppen at verificerer at de nødvendige metoder på OpenCvs implementations kode bliver invokeret med de korrekte parameter. Dette hjælpe gruppen med at sikre sig at den kode som interagerer med implementationen af det virker.

## Testing the Ellipse Dectector
At teste `EllipseDectector` klassen vidste sig at være lidt af en udfordring. Ikke grundet at koden nødvendigvis var svær at teste, men mere at sikrer sig at de rigtige aspekter af klassen blev testet. Slut produktet blev en sæt af 4 meget enkelte tests som består af:

1. Sikrer den kan gendkende en rød cirkler.
2. Sikrer den ikke gendkende andre farver cirkler.
3. Sikrer den ikke gendkender andre røde geometriske former som en rød cirkle.
4. Sikrer den kan gendkende en rød cirkle bland mange andre røde objekter.

Disse 4 scenarier blvier testet ved hjælp af 4 simple billeder som hver repræsenterer et scenarie og der verificeres der efter at detektoren returnerer henholdvis `null` eller `not null`.

Årsagen til at testen ikke er mere specifikt skyldes forskellige årsager, blandt andet er der mange finjusterings parameter i klassen som kan have effekt på hvorledes den genkender cirklerne, dette betyder at en finjustering på nogle parameter vil påvirke selve dataen returneret fra detektoren, men detektoren ville stadig genkende ellipsen fuldt acceptabelt. Dermed bør testen ikke fejle i sådan et tilfælde. En anden årsag er at testens opgave var ikke at sikrer en specifikt implementation af en ellipse detektor, blot at den implementation rent faktisk er i stand til at detekterer en ellipse.

## Future work regarding testing
Selve test suiten er ikke optimal, hvis projektet bruges til andet end studie relateret, vil gruppen på det stærkest anbefale at test suiten forbedres.

Dette kan blandt andet gøres ved at lave nogle flere tests til `EllipseDectector` klassen, hvor der kunne defineres et minimums krav til algorithmen og der derved kunne garanteres mulighed for detektion i forhold til minimumskravende. Med minimumskrav kunne der kikkes på parameterer så som belysning, afstand til skiltet, vinkel fra kamera til skiltet, beregningshastighed og flere. 

Der udover vil gruppen anbefale at der skrives en mere fyldetsgørende test til `CircleCropper` klassen da den nuværende testning af denne klasse er meget mangelfuld. 

Der kan også være værdi i at teste noget af implementations koden fra OpenCV, hvis projektet skal bruges til eksempelvis kommerciel brug, vil der være afhængigheder fra OpenCV som bør testes for at sikrer der ikke sker utilsigtede hændelser.

Den sidste overvejelse omkring fremtidig arbejde omkring testing er at projektets test suite på nuværende tidspunkt bærer meget præg af at test såkaldte `Happy paths` altså teste at koden agerer som den skal når inputs er korrekte. Der er et generelt mangel i at teste hvorledes koden agerer på inkorrekt input, og sikring af at det ikke vil medfører uhensigtsmæssige resultater.



