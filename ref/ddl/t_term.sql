-- Drop table

-- DROP TABLE public.t_term;

CREATE TABLE public.t_term (
	term_type varchar(16) NOT NULL, -- type or group
	term_code varchar(16) NOT NULL, -- code
	is_act int2 NULL, -- is actvie or not
	term_name_kr varchar(32) NULL, -- korean name
	term_name_en varchar(32) NULL, -- english name
	ref_type varchar(16) NULL, -- reference type or group
	ref_code varchar(16) NULL, -- reference code
	dscr varchar(128) NULL, -- description
	CONSTRAINT uni_t_term UNIQUE (term_type, term_code)
);
COMMENT ON TABLE public.t_term IS 'terms';

-- Column comments

COMMENT ON COLUMN public.t_term.term_type IS 'type or group';
COMMENT ON COLUMN public.t_term.term_code IS 'code';
COMMENT ON COLUMN public.t_term.is_act IS 'is actvie or not';
COMMENT ON COLUMN public.t_term.term_name_kr IS 'korean name';
COMMENT ON COLUMN public.t_term.term_name_en IS 'english name';
COMMENT ON COLUMN public.t_term.ref_type IS 'reference type or group';
COMMENT ON COLUMN public.t_term.ref_code IS 'reference code';
COMMENT ON COLUMN public.t_term.dscr IS 'description';

COMMIT;

DELETE FROM public.t_term;
INSERT INTO public.t_term (term_type,term_code,is_act,term_name_kr,term_name_en,ref_type,ref_code,dscr) VALUES 
('fin','item_code',1,'종목코드','item code','fin','item_code','종목코드'),
,('fin','shar_trsr',1,'자기주식수','treasury shares','fin','shar_trsr','treasury shares')
,('fin','intf',1,'인터페이스','interface','fin','intf','interface')
,('fin','shar_outs',1,'유통주식수','outstanding shares','fin','shar_outs','outstanding shares')
,('fin','etf',1,'ETF','exchange traded fund','fin','etf','exchange traded fund')
,('fin','item_name_kr',1,'한글종목명','korean item name','fin','item_name_kr','한글종목명')
,('fin','item_name_en',1,'영어종목명','english item name','fin','item_name_en','영어종목명')
,('fin','indx_name_kr',1,'한글지수명','korean index name','fin','indx_name','한글지수명')
,('fin','date_set',1,'최초설정일','setting date','fin','date_set','최초설정일')
,('fin','date_list',1,'상장일','listed date','fin','date_list','listed date')
,('fin','asst_clss',1,'자산군','asset class','fin','asst_clss','asset class')
,('asst_clss','bond',1,'채권','bonds','asst_clss','bond','bonds')
,('asst_clss','equity',1,'주식','equities or stocks','asst_clss','equity','equities or stocks')
,('fin','expn_rate',1,'운용비용','expense ratio','fin','expn_rate','Total Annual Fund Operating Expense(= Management Fees + Service(12b-1) Fees + Acquired Fund Fees and Expenses(AFFE)) - Fee Waiver, 전체비용(=펀드운영비+광고/마케팅비+추가비용) - 할인')
,('fin','acct_perd',1,'회계기간','accounting period','fin','acct_perd','accounting period')
,('fin','date_dstb',1,'분배금기준일','distribution base date','fin','date_dstb','distribution base date')
,('fin','issr',1,'자산운용사','issuer','fin','issr','issuer')
,('fin','issr_url',1,'자산운용사 홈페이지','issuer url','fin','issr_url','issuer url')
,('fin','item_dscr',1,'상품설명','fullstory','fin','item_dscr','상품설명')
,('fin','cu',1,'구성종목','customs union','fin','cu','A customs union is generally defined as a type of trade bloc which is composed of a free trade area with a common external tariff')
;