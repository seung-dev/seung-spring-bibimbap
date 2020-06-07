-- Drop table

-- DROP TABLE public.t_iso_639_1;

CREATE TABLE public.t_iso_639_1 (
	code varchar(2) NOT NULL, -- lanuage code
	exonym varchar(128) NULL, -- english name
	endonym varchar(128) NULL, -- endonym
	dscr varchar(128) NULL, -- description
	CONSTRAINT pk_t_iso_639_1 PRIMARY KEY (code)
);
COMMENT ON TABLE public.t_iso_639_1 IS 'iso_639_1(https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes)';

-- Column comments

COMMENT ON COLUMN public.t_iso_639_1.code IS 'lanuage code';
COMMENT ON COLUMN public.t_iso_639_1.exonym IS 'english name';
COMMENT ON COLUMN public.t_iso_639_1.endonym IS 'endonym';
COMMENT ON COLUMN public.t_iso_639_1.dscr IS 'description';

COMMIT;

INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('ab','Abkhazian','аҧсуа бызшәа, аҧсшәа','')
,('aa','Afar','Afaraf','')
,('af','Afrikaans','Afrikaans','')
,('ak','Akan','Akan','')
,('sq','Albanian','Shqip','')
,('am','Amharic','አማርኛ','')
,('ar','Arabic','العربية','')
,('an','Aragonese','aragonés','')
,('hy','Armenian','Հայերեն','')
,('as','Assamese','অসমীয়া','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('av','Avaric','авар мацӀ, магӀарул мацӀ','')
,('ae','Avestan','avesta','')
,('ay','Aymara','aymar aru','')
,('az','Azerbaijani','azərbaycan dili','')
,('bm','Bambara','bamanankan','')
,('ba','Bashkir','башҡорт теле','')
,('eu','Basque','euskara, euskera','')
,('be','Belarusian','беларуская мова','')
,('bn','Bengali','বাংলা','')
,('bh','Bihari languages','भोजपुरी','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('bi','Bislama','Bislama','')
,('bs','Bosnian','bosanski jezik','')
,('br','Breton','brezhoneg','')
,('bg','Bulgarian','български език','')
,('my','Burmese','ဗမာစာ','')
,('ca','Catalan, Valencian','català, valencià','')
,('ch','Chamorro','Chamoru','')
,('ce','Chechen','нохчийн мотт','')
,('ny','Chichewa, Chewa, Nyanja','chiCheŵa, chinyanja','')
,('zh','Chinese','中文 (Zhōngwén), 汉语, 漢語','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('cv','Chuvash','чӑваш чӗлхи','')
,('kw','Cornish','Kernewek','')
,('co','Corsican','corsu, lingua corsa','')
,('cr','Cree','ᓀᐦᐃᔭᐍᐏᐣ','')
,('hr','Croatian','hrvatski jezik','')
,('cs','Czech','čeština, český jazyk','')
,('da','Danish','dansk','')
,('dv','Divehi, Dhivehi, Maldivian','ދިވެހި','')
,('nl','Dutch, Flemish','Nederlands, Vlaams','')
,('dz','Dzongkha','རྫོང་ཁ','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('en','English','English','')
,('eo','Esperanto','Esperanto','')
,('et','Estonian','eesti, eesti keel','')
,('ee','Ewe','Eʋegbe','')
,('fo','Faroese','føroyskt','')
,('fj','Fijian','vosa Vakaviti','')
,('fi','Finnish','suomi, suomen kieli','')
,('fr','French','français, langue française','')
,('ff','Fulah','Fulfulde, Pulaar, Pular','')
,('gl','Galician','Galego','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('ka','Georgian','ქართული','')
,('de','German','Deutsch','')
,('el','Greek, Modern (1453–)','ελληνικά','')
,('gu','Gujarati','ગુજરાતી','')
,('ht','Haitian, Haitian Creole','Kreyòl ayisyen','')
,('ha','Hausa','(Hausa) هَوُسَ','')
,('he','Hebrew','עברית','')
,('hz','Herero','Otjiherero','')
,('hi','Hindi','हिन्दी, हिंदी','')
,('ho','Hiri Motu','Hiri Motu','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('hu','Hungarian','magyar','')
,('ia','Interlingua (International Auxiliary Language Association)','Interlingua','')
,('id','Indonesian','Bahasa Indonesia','')
,('ie','Interlingue, Occidental','(originally:) Occidental, (after WWII:) Interlingue','')
,('ga','Irish','Gaeilge','')
,('ig','Igbo','Asụsụ Igbo','')
,('ik','Inupiaq','Iñupiaq, Iñupiatun','')
,('io','Ido','Ido','')
,('is','Icelandic','Íslenska','')
,('it','Italian','Italiano','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('iu','Inuktitut','ᐃᓄᒃᑎᑐᑦ','')
,('ja','Japanese','日本語 (にほんご)','')
,('jv','Javanese','ꦧꦱꦗꦮ, Basa Jawa','')
,('kl','Kalaallisut, Greenlandic','kalaallisut, kalaallit oqaasii','')
,('kn','Kannada','ಕನ್ನಡ','')
,('kr','Kanuri','Kanuri','')
,('ks','Kashmiri','कश्मीरी, كشميري‎','')
,('kk','Kazakh','қазақ тілі','')
,('km','Central Khmer','ខ្មែរ, ខេមរភាសា, ភាសាខ្មែរ','')
,('ki','Kikuyu, Gikuyu','Gĩkũyũ','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('rw','Kinyarwanda','Ikinyarwanda','')
,('ky','Kirghiz, Kyrgyz','Кыргызча, Кыргыз тили','')
,('kv','Komi','коми кыв','')
,('kg','Kongo','Kikongo','')
,('ko','Korean','한국어','')
,('ku','Kurdish','Kurdî, کوردی‎','')
,('kj','Kuanyama, Kwanyama','Kuanyama','')
,('la','Latin','latine, lingua latina','')
,('lb','Luxembourgish, Letzeburgesch','Lëtzebuergesch','')
,('lg','Ganda','Luganda','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('li','Limburgan, Limburger, Limburgish','Limburgs','')
,('ln','Lingala','Lingála','')
,('lo','Lao','ພາສາລາວ','')
,('lt','Lithuanian','lietuvių kalba','')
,('lu','Luba-Katanga','Kiluba','')
,('lv','Latvian','latviešu valoda','')
,('gv','Manx','Gaelg, Gailck','')
,('mk','Macedonian','македонски јазик','')
,('mg','Malagasy','fiteny malagasy','')
,('ms','Malay','Bahasa Melayu, بهاس ملايو‎','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('ml','Malayalam','മലയാളം','')
,('mt','Maltese','Malti','')
,('mi','Maori','te reo Māori','')
,('mr','Marathi','मराठी','')
,('mh','Marshallese','Kajin M̧ajeļ','')
,('mn','Mongolian','Монгол хэл','')
,('na','Nauru','Dorerin Naoero','')
,('nv','Navajo, Navaho','Diné bizaad','')
,('nd','North Ndebele','isiNdebele','')
,('ne','Nepali','नेपाली','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('ng','Ndonga','Owambo','')
,('nb','Norwegian Bokmål','Norsk Bokmål','')
,('nn','Norwegian Nynorsk','Norsk Nynorsk','')
,('no','Norwegian','Norsk','')
,('ii','Sichuan Yi, Nuosu','ꆈꌠ꒿ Nuosuhxop','')
,('nr','South Ndebele','isiNdebele','')
,('oj','Ojibwa','ᐊᓂᔑᓈᐯᒧᐎᓐ','')
,('cu','Church Slavic, Old Slavonic, Church Slavonic, Old Bulgarian, Old Church Slavonic','ѩзыкъ словѣньскъ','')
,('om','Oromo','Afaan Oromoo','')
,('or','Oriya','ଓଡ଼ିଆ','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('os','Ossetian, Ossetic','ирон æвзаг','')
,('pa','Punjabi, Panjabi','ਪੰਜਾਬੀ, پنجابی‎','')
,('pi','Pali','पालि, पाळि','')
,('fa','Persian','فارسی','')
,('pl','Polish','język polski, polszczyzna','')
,('ps','Pashto, Pushto','پښتو','')
,('pt','Portuguese','Português','')
,('qu','Quechua','Runa Simi, Kichwa','')
,('rm','Romansh','Rumantsch Grischun','')
,('rn','Rundi','Ikirundi','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('ro','Romanian, Moldavian, Moldovan','Română','')
,('ru','Russian','русский','')
,('sa','Sanskrit','संस्कृतम्','')
,('sc','Sardinian','sardu','')
,('sd','Sindhi','सिन्धी, سنڌي، سندھی‎','')
,('se','Northern Sami','Davvisámegiella','')
,('sm','Samoan','gagana fa''a Samoa','')
,('sg','Sango','yângâ tî sängö','')
,('sr','Serbian','српски језик','')
,('gd','Gaelic, Scottish Gaelic','Gàidhlig','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('sn','Shona','chiShona','')
,('si','Sinhala, Sinhalese','සිංහල','')
,('sk','Slovak','Slovenčina, Slovenský Jazyk','')
,('sl','Slovenian','Slovenski Jezik, Slovenščina','')
,('so','Somali','Soomaaliga, af Soomaali','')
,('st','Southern Sotho','Sesotho','')
,('es','Spanish, Castilian','Español','')
,('su','Sundanese','Basa Sunda','')
,('sw','Swahili','Kiswahili','')
,('ss','Swati','SiSwati','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('sv','Swedish','Svenska','')
,('ta','Tamil','தமிழ்','')
,('te','Telugu','తెలుగు','')
,('tg','Tajik','тоҷикӣ, toçikī, تاجیکی‎','')
,('th','Thai','ไทย','')
,('ti','Tigrinya','ትግርኛ','')
,('bo','Tibetan','བོད་ཡིག','')
,('tk','Turkmen','Türkmen, Түркмен','')
,('tl','Tagalog','Wikang Tagalog','')
,('tn','Tswana','Setswana','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('to','Tonga (Tonga Islands)','Faka Tonga','')
,('tr','Turkish','Türkçe','')
,('ts','Tsonga','Xitsonga','')
,('tt','Tatar','татар теле, tatar tele','')
,('tw','Twi','Twi','')
,('ty','Tahitian','Reo Tahiti','')
,('ug','Uighur, Uyghur','ئۇيغۇرچە‎, Uyghurche','')
,('uk','Ukrainian','Українська','')
,('ur','Urdu','اردو','')
,('uz','Uzbek','Oʻzbek, Ўзбек, أۇزبېك‎','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('ve','Venda','Tshivenḓa','')
,('vi','Vietnamese','Tiếng Việt','')
,('vo','Volapük','Volapük','')
,('wa','Walloon','Walon','')
,('cy','Welsh','Cymraeg','')
,('wo','Wolof','Wollof','')
,('fy','Western Frisian','Frysk','')
,('xh','Xhosa','isiXhosa','')
,('yi','Yiddish','ייִדיש','')
,('yo','Yoruba','Yorùbá','')
;
INSERT INTO public.t_iso_639_1 (code,exonym,endonym,dscr) VALUES 
('za','Zhuang, Chuang','Saɯ cueŋƅ, Saw cuengh','')
,('zu','Zulu','isiZulu','')
,('gn','Guarani','Avañe''ẽ','')
,('oc','Occitan','occitan, lenga d''òc','')
;