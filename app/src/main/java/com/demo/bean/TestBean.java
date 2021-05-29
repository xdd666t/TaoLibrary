package com.demo.bean;

import java.util.List;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/1/31 14:56
 */
public class TestBean {


    /**
     * count : 1
     * start : 0
     * total : 2104
     * books : [{"rating":{"max":10,"numRaters":28470,"average":"8.8","min":0},"subtitle":"","author":["吴承恩"],"pubdate":"2004-8","tags":[{"count":3759,"name":"古典文学","title":"古典文学"},{"count":3014,"name":"西游记","title":"西游记"},{"count":2410,"name":"小说","title":"小说"},{"count":2011,"name":"古典名著","title":"古典名著"},{"count":1498,"name":"名著","title":"名著"},{"count":1376,"name":"中国","title":"中国"},{"count":1363,"name":"中国文学","title":"中国文学"},{"count":1310,"name":"经典","title":"经典"}],"origin_title":"","image":"https://img3.doubanio.com/view/subject/m/public/s1627374.jpg","binding":"平装","translator":["黄肃秋 注释"],"catalog":"第一回 灵根育孕源流出 心性修持大道生\n第二回 悟彻菩提真妙理 断魔归本合元神\n第三回 四海千山皆拱伏 九幽十类尽除名\n第四回 官封弼马心何足 名注齐天意未宁\n第五回 乱蟠桃大圣偷丹 反天宫诸神捉怪\n第六回 观音赴会问原因 小圣施威降大圣\n第七回 八封炉中逃大圣 五行山下定心猿\n第八回 我佛造经传极乐 观音奉旨上长安\n第九回 袁守诚妙算无私曲 老龙王拙计犯天条\n第十回 二将军宫门镇鬼 唐太宗地府还魂\n第十一回 还受生唐王遵善果 度孤魂瑀萧正空门\n第十二回 玄奘秉诚建大会 观音显相化金蝉\n第十三回 陷虎穴金星解厄 双叉岭伯钦留僧\n第十四回 心猿归正 六贼无踪\n第十五回 蛇盘山诸神暗佑 鹰愁涧意马收缰\n第十六回 观音院僧谋宝贝 黑风山怪窃袈裟\n第十七回 孙行者大闹黑风山 观世音收伏熊罴怪\n第十八回 观音院唐僧脱难 高老庄大圣除魔\n第十九回 云栈洞悟空收八戒 浮屠山玄奘受心经\n第二 十回 黄风岭唐僧有难 半山中八戒争先\n第二十一回 护法设庄留大圣 须弥灵吉定风魔\n第二十二回 八戒大战流沙河 木叉奉法收悟净\n第二十三回 三藏不忘本 四圣试禅心\n第二十四回 万寿山大仙留故友 五庄观行者窃人参\n第二十五回 镇元仙赶捉取经僧 孙行者大闹五庄观\n第二十六回 孙悟空三岛求方 观世音甘泉活树\n第二十七回 尸魔三戏唐三藏 圣僧恨逐美猴王\n第二十八回 花果山群妖聚义 黑松林三藏逢魔\n第二十九回 脱难江流来国土 承恩八戒转山林\n第三 十回 邪魔侵正法 意马忆心猿\n第三十一回 猪八戒义激猴王 孙行者智降妖怪\n第三十二回 平顶山功曹传信 莲花洞木母逢灾\n第三十三回 外道迷真性 元神助本心\n第三十四回 魔王巧算困心猿 大圣腾挪骗宝贝\n第三十五回 外道施威欺正性 心猿获宝伏邪魔\n第三十六回 心猿正处诸缘伏 劈破旁门见月明\n第三十七回 鬼王夜谒唐三藏 悟空神化引婴儿\n第三十八回 婴儿问母知邪正 金木参玄见假真\n第三十九回 一粒金丹天上得 三年故主世间生\n第四 十回 婴儿戏化禅心乱 猿马刀圭木母空\n第四十一回 心猿遭火败 木母被魔擒\n第四十二回 大圣殷勤拜南海 观音慈善缚红孩\n第四十三回 黑河妖孽擒僧去 西洋龙子捉鼍回\n第四十四回 法身元运逢车力 心正妖邪度脊关\n第四十五回 三清观大圣留名 车迟国猴王显法\n第四十六回 外道弄强欺正法 心猿显圣灭诸邪\n第四十七回 圣僧夜阻通天水 金木垂慈救小童\n第四十八回 魔弄寒风飘大雪 僧思拜佛履层冰\n第四十九回 三藏有灾沉水宅 观音救难现鱼篮\n第五 十回 情乱性从因爱欲 神昏心动遇魔头\n第五十一回 心猿空用千般计 水火无功难炼魔\n第五十二回 悟空大闹金（山兜）洞 如来暗示主人公\n第五十三回 禅主吞餐怀鬼孕 黄婆运水解邪胎\n第五十四回 法性西来逢女国 心猿定计脱烟花\n第五十五回 色邪淫戏唐三藏 性正修持不坏身\n第五十六回 神狂诛草寇 道昧放心猿\n第五十七回 真行者落伽山诉苦 假猴王水帘洞誊文\n第五十八回 二心搅乱大乾坤 一体难修真寂灭\n第五十九回 唐三藏路阻火焰山 孙行者一调芭蕉扇\n第六 十回 牛魔王罢战赴华筵 孙行者二调芭蕉扇\n第六十一回 猪八戒助力败魔王 孙行者三调芭蕉扇\n第六十二回 涤垢洗心惟扫塔 缚魔归正乃修身\n第六十三回 二僧荡怪闹龙宫 群圣除邪获宝贝\n第六十四回 荆棘岭悟能努力 木仙庵三藏谈诗\n第六十五回 妖邪假设小雷音 四众皆遭大厄难\n第六十六回 诸神遭毒手 弥勒缚妖魔\n第六十七回 拯救驼罗禅性稳 脱离秽污道心清\n第六十八回 朱紫国唐僧论前世 孙行者施为三折肱\n第六十九回 心主夜间修药物 君王筵上论妖邪\n第七 十回 妖魔宝放烟沙火 悟空计盗紫金铃\n第七十一回 行者假名降怪犼 观音现相伏妖王\n第七十二回 盘丝洞七情迷本 濯垢泉八戒忘形\n第七十三回 情因旧恨生灾毒 心主遭魔幸破光\n第七十四回 长庚传报魔头狠 行者施为变化能\n第七十五回 心猿钻透阴阳窍 魔王还归大道真\n第七十六回 心神居舍魔归性 木母同降怪体真\n第七十七回 群魔欺本性 一体拜真如\n第七十八回 比丘怜子遣阴神 金殿识魔谈道德\n第七十九回 寻洞擒妖逢老寿 当朝正主救婴儿\n第八 十回 姹女育阳求配偶 心猿护主识妖邪\n第八十一回 镇海寺心猿知怪 黑松林三众寻师\n第八十二回 姹女求阳 元神护道\n第八十三回 心猿识得丹头 姹女还归本性\n第八十四回 难灭伽持圆大觉 法王成正体天然\n第八十五回 心猿妒木母 魔主计吞禅\n第八十六回 木母助威征怪物 金公施法灭妖邪\n第八十七回 凤仙郡冒天致旱 孙大圣劝善施霖\n第八十八回 禅到玉华施法会 心猿木母授门人\n第八十九回 黄狮精虚设钉钯宴 金木土计闹豹头山\n第九十回 师狮授受同归一 盗道缠禅静九灵\n第九十一回 金平府元夜观灯 玄英洞唐僧供状\n第九十二回 三僧大战青龙山 四星挟捉犀牛怪\n第九十三回 给孤园问古谈因 天竺国朝王遇偶\n第九十四回 四僧宴乐御花园 一怪空怀情欲喜\n第九十五回 假合形骸擒玉兔 真阴归正会灵元\n第九十六回 寇员外喜待高僧 唐长老不贪富贵\n第九十七回 金酬外护遭魔毒 圣显幽魂救本原\n第九十八回 猿熟马驯方脱壳 功成行满见真如\n第九十九回 九九数完魔灭尽 三三行满道归根\n第一百回 径回东土 五圣成真\n校点后记","pages":"1198","images":{"small":"https://img3.doubanio.com/view/subject/s/public/s1627374.jpg","large":"https://img3.doubanio.com/view/subject/l/public/s1627374.jpg","medium":"https://img3.doubanio.com/view/subject/m/public/s1627374.jpg"},"alt":"https://book.douban.com/subject/1029553/","id":"1029553","publisher":"人民文学出版社","isbn10":"7020008739","isbn13":"9787020008735","title":"西游记（全二册）","url":"https://api.douban.com/v2/book/1029553","alt_title":"","author_intro":"吴承恩，字汝忠，号射阳山人。淮安府山阳县（今江苏淮安）人。他生活在明代中叶，约当公元1500年到1582年间；出生在一个由文职小官僚而沦落为小商人的家庭。他极好读书，但常遭官府吏胥敲诈，对社会现实颇为不满。","summary":"《西游记》主要描写的是孙悟空保唐僧西天取经，历经九九八十一难的故事。唐僧取经是历史上一件真实的事。大约距今一千三百多年前，即唐太宗贞观元年（627），年仅25岁的青年和尚玄奘离开京城长安，只身到天竺（印度）游学。他从长安出发后，途经中亚、阿富汗、巴基斯坦，历尽艰难险阻，最后到达了印度。他在那里学习了两年多，并在一次大型佛教经学辩论会任主讲，受到了赞誉。贞观十九年（645）玄奘回到了长安，带回佛经657部。他这次西天取经，前后十九年，行程几万里，是一次传奇式的万里长征，轰动一时。后来玄奘口述西行见闻，由弟子辩机辑录成《大唐西域记》十二卷。但这部书主要讲述了路上所见各国的历史、地理及交通，没有什么故事。及到他的弟子慧立、彦琮撰写的《大唐大慈恩寺三藏法师传》，则为玄奘的经历增添了许多神话色彩，从此，唐僧取经的故事便开始在民间广为流传。南宋有《大唐三藏取经诗话》，金代院本有《唐三藏》、《蟠桃会》等，元杂剧有吴昌龄的《唐三藏西天取经》、无名氏的《二郎神锁齐大圣》等，这些都为《西游记》的创作奠定了基础。吴承恩也正是在民间传说和话本、戏曲的基础上，经过艰苦的再创造，完成了这部令中华民族为之骄傲的伟大大文学巨著。吴承恩，字汝忠，号射阳山人，淮安府山阳（今江苏省淮安市）人。约生于明弘治十三年至正德初年之间（1500\u20141510），约卒于万历十年（1582）。吴承恩的曾祖父、祖父都是读书人，任过县学的训导、教谕。但到了他父亲吴锐这一辈，由于家贫困，出赘徐家，\u201c遂袭徐氏业，坐肆中\u201d，当起了小商人。尽管如此，吴家却不失读书的传统。据说其父吴锐虽为商人，不仅为人正派，而且好读书，好谈时政，这自然对吴承恩产生较大影响。吴承恩从小就很聪明，很早入了学，少年得志，名满乡里。天启《淮安府志》卷十六说吴承恩\u201c性敏而多慧，博极群书，为诗文，下笔立成。\u201d但成年后的吴承恩却很不顺利，在科举进身的道路上屡遭挫折，到四十多岁才补了一个岁贡生，五十多岁任过浙江长兴县丞，后又担任过荆王府纪善，这是同县丞级别差不多的闲职。吴承恩创作《西游记》大约是中年以后，或认为是晚年所作，具体时间无法确定。除《西游记》外，他还创作有长诗《二郎搜山图歌》和《禹鼎志》。现存《射阳先生存稿》四卷，包括诗一卷、散文三卷，是吴承恩逝世后由丘度编订而成。 《西游记》全书一百回，从大的结构上看，可分成三个部分。第一回至第八回是第一部分，主要写了孙悟空出世、拜师、大闹天宫，这是全书最精彩的章节，热闹非凡，孙悟空上天入地好一顿折腾，将他的反抗性格表现得淋漓尽致。第八回至第十二回是第二部分，主要写了唐僧的出身及取经的缘由。第十三回至最后一回是第三部分，主要写唐僧西天取经，路上先后收了孙悟空、猪八戒、沙和尚三个徒弟，并历经九九八十一难，终于取到了真经，修成了正果。 《西游记》向人们展示了一个绚丽多彩的神魔世界，人们无不在作者丰富而大胆的艺术想象面前惊叹不已。然而，任何一部文学作品都是一定社会生活的反映，作为神魔小说杰出代表的《西游记》亦不例外。正如鲁迅先生在《中国小说史略》中指出，《西游记》\u201c讽刺揶揄则取当时世态，加以铺张描写\u201d。又说：\u201c作者禀性，\u2018复善谐剧\u2019，故虽述变幻恍忽之事，亦每杂解颐之言，使神魔皆有人情，精魅亦通世故。\u201d的确如此。 通过《西游记》中虚幻的神魔世界，我们处处可以看到现实社会的投影。如在孙悟空的形象创造上，就寄托了作者的理想。 孙悟空那种不屈不挠的斗争精神，奋起金箍棒，横扫一切妖魔鬼怪的大无畏气概，反映了人民的愿望和要求。他代表了一种正义的力量，表现出人民战胜一切困难的必胜信念。又如取经路上遇到的那些妖魔，或是自然灾难的幻化，或是邪恶势力的象征。他们的贪婪、凶残、阴险和狡诈，也正是封建社会里的黑暗势力的特点。不仅如此，玉皇大帝统治的天宫、如来佛祖管辖的西方极乐世界，也都浓浓地涂上了人间社会的色彩。而作者对封建社会最高统治者的态度也颇可玩味，在《西游记》中，简直找不出一个称职的皇帝；至于昏聩无能的玉皇大帝、宠信妖怪的车迟国国王、要将小儿心肝当药引子的比丘国国王，则不是昏君就是暴君。对这些形象的刻画，即使是信手拈来，也无不具有很强的现实意义。 《西游记》不仅有较深刻的思想内容，艺术上也取得了很高的成就。它以丰富奇特的艺术想象、生动曲折的故事情节，栩栩如生的人物形象，幽默诙谐的语言，构筑了一座独具特色的《西游记》艺术宫殿。但我认为，《西游记》在艺术上的最大成就，是成功地创造了孙悟空、猪八戒这两个不朽的艺术形象。 孙悟空是《西游记》中第一主人公，是个非常了不起的英雄。他有无穷的本领，天不怕地不怕，具有不屈的反抗精神。他有着大英雄的不凡气度，也有爱听恭维话的缺点。他机智勇敢又诙谐好闹。而他最大的特点就是敢斗。与至高至尊的玉皇大帝敢斗，楞是叫响了\u201c齐天大圣\u201d的美名；与妖魔鬼怪敢斗，火眼金睛决不放过一个妖魔，如意金箍棒下决不对妖魔留情；与一切困难敢斗，决不退却低头。这就是孙悟空，一个光彩夺目的神话英雄。说到猪八戒，他的本事比孙悟空可差远了，更谈不上什么光辉高大，但这个形象同样刻画得非常好。 猪八戒是一个喜剧形象，他憨厚老实，有力气，也敢与妖魔作斗争，是孙悟空第一得力助手。但他又满身毛病，如好吃，好占小便宜，好女色，怕困难，常常要打退堂鼓，心里老想着高老庄的媳妇；他有时爱撒个谎，可笨嘴拙腮的又说不圆；他还时不时地挑拨唐僧念紧箍咒，让孙悟空吃点苦头；他甚至还藏了点私房钱，塞在耳朵里。他的毛病实在多，这正是小私有者的恶习。作者对猪八戒缺点的批评是很严厉的，但又是善意的。他并不是一个被否定的人物，因此人们并不厌恶猪八戒，相反却感到十分真实可爱。唐僧的形象写得也不错，但比起孙悟空、猪八戒来，则要逊色得多。沙僧更是缺少鲜明的性格特点，这不能不说是《西游记》的缺憾。尽管如此，《西游记》在艺术上取得的成就仍是十分惊人的，孙悟空、猪八戒这两个形象，以其鲜明的个性特征，在中国文学史上立起了一座不朽的艺术丰碑。","series":{"id":"106","title":"中国古典文学读本丛书"},"price":"47.20元"}]
     */

    private int count;
    private int start;
    private int total;
    private List<BooksBean> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        /**
         * rating : {"max":10,"numRaters":28470,"average":"8.8","min":0}
         * subtitle :
         * author : ["吴承恩"]
         * pubdate : 2004-8
         * tags : [{"count":3759,"name":"古典文学","title":"古典文学"},{"count":3014,"name":"西游记","title":"西游记"},{"count":2410,"name":"小说","title":"小说"},{"count":2011,"name":"古典名著","title":"古典名著"},{"count":1498,"name":"名著","title":"名著"},{"count":1376,"name":"中国","title":"中国"},{"count":1363,"name":"中国文学","title":"中国文学"},{"count":1310,"name":"经典","title":"经典"}]
         * origin_title :
         * image : https://img3.doubanio.com/view/subject/m/public/s1627374.jpg
         * binding : 平装
         * translator : ["黄肃秋 注释"]
         * catalog : 第一回 灵根育孕源流出 心性修持大道生
         第二回 悟彻菩提真妙理 断魔归本合元神
         第三回 四海千山皆拱伏 九幽十类尽除名
         第四回 官封弼马心何足 名注齐天意未宁
         第五回 乱蟠桃大圣偷丹 反天宫诸神捉怪
         第六回 观音赴会问原因 小圣施威降大圣
         第七回 八封炉中逃大圣 五行山下定心猿
         第八回 我佛造经传极乐 观音奉旨上长安
         第九回 袁守诚妙算无私曲 老龙王拙计犯天条
         第十回 二将军宫门镇鬼 唐太宗地府还魂
         第十一回 还受生唐王遵善果 度孤魂瑀萧正空门
         第十二回 玄奘秉诚建大会 观音显相化金蝉
         第十三回 陷虎穴金星解厄 双叉岭伯钦留僧
         第十四回 心猿归正 六贼无踪
         第十五回 蛇盘山诸神暗佑 鹰愁涧意马收缰
         第十六回 观音院僧谋宝贝 黑风山怪窃袈裟
         第十七回 孙行者大闹黑风山 观世音收伏熊罴怪
         第十八回 观音院唐僧脱难 高老庄大圣除魔
         第十九回 云栈洞悟空收八戒 浮屠山玄奘受心经
         第二 十回 黄风岭唐僧有难 半山中八戒争先
         第二十一回 护法设庄留大圣 须弥灵吉定风魔
         第二十二回 八戒大战流沙河 木叉奉法收悟净
         第二十三回 三藏不忘本 四圣试禅心
         第二十四回 万寿山大仙留故友 五庄观行者窃人参
         第二十五回 镇元仙赶捉取经僧 孙行者大闹五庄观
         第二十六回 孙悟空三岛求方 观世音甘泉活树
         第二十七回 尸魔三戏唐三藏 圣僧恨逐美猴王
         第二十八回 花果山群妖聚义 黑松林三藏逢魔
         第二十九回 脱难江流来国土 承恩八戒转山林
         第三 十回 邪魔侵正法 意马忆心猿
         第三十一回 猪八戒义激猴王 孙行者智降妖怪
         第三十二回 平顶山功曹传信 莲花洞木母逢灾
         第三十三回 外道迷真性 元神助本心
         第三十四回 魔王巧算困心猿 大圣腾挪骗宝贝
         第三十五回 外道施威欺正性 心猿获宝伏邪魔
         第三十六回 心猿正处诸缘伏 劈破旁门见月明
         第三十七回 鬼王夜谒唐三藏 悟空神化引婴儿
         第三十八回 婴儿问母知邪正 金木参玄见假真
         第三十九回 一粒金丹天上得 三年故主世间生
         第四 十回 婴儿戏化禅心乱 猿马刀圭木母空
         第四十一回 心猿遭火败 木母被魔擒
         第四十二回 大圣殷勤拜南海 观音慈善缚红孩
         第四十三回 黑河妖孽擒僧去 西洋龙子捉鼍回
         第四十四回 法身元运逢车力 心正妖邪度脊关
         第四十五回 三清观大圣留名 车迟国猴王显法
         第四十六回 外道弄强欺正法 心猿显圣灭诸邪
         第四十七回 圣僧夜阻通天水 金木垂慈救小童
         第四十八回 魔弄寒风飘大雪 僧思拜佛履层冰
         第四十九回 三藏有灾沉水宅 观音救难现鱼篮
         第五 十回 情乱性从因爱欲 神昏心动遇魔头
         第五十一回 心猿空用千般计 水火无功难炼魔
         第五十二回 悟空大闹金（山兜）洞 如来暗示主人公
         第五十三回 禅主吞餐怀鬼孕 黄婆运水解邪胎
         第五十四回 法性西来逢女国 心猿定计脱烟花
         第五十五回 色邪淫戏唐三藏 性正修持不坏身
         第五十六回 神狂诛草寇 道昧放心猿
         第五十七回 真行者落伽山诉苦 假猴王水帘洞誊文
         第五十八回 二心搅乱大乾坤 一体难修真寂灭
         第五十九回 唐三藏路阻火焰山 孙行者一调芭蕉扇
         第六 十回 牛魔王罢战赴华筵 孙行者二调芭蕉扇
         第六十一回 猪八戒助力败魔王 孙行者三调芭蕉扇
         第六十二回 涤垢洗心惟扫塔 缚魔归正乃修身
         第六十三回 二僧荡怪闹龙宫 群圣除邪获宝贝
         第六十四回 荆棘岭悟能努力 木仙庵三藏谈诗
         第六十五回 妖邪假设小雷音 四众皆遭大厄难
         第六十六回 诸神遭毒手 弥勒缚妖魔
         第六十七回 拯救驼罗禅性稳 脱离秽污道心清
         第六十八回 朱紫国唐僧论前世 孙行者施为三折肱
         第六十九回 心主夜间修药物 君王筵上论妖邪
         第七 十回 妖魔宝放烟沙火 悟空计盗紫金铃
         第七十一回 行者假名降怪犼 观音现相伏妖王
         第七十二回 盘丝洞七情迷本 濯垢泉八戒忘形
         第七十三回 情因旧恨生灾毒 心主遭魔幸破光
         第七十四回 长庚传报魔头狠 行者施为变化能
         第七十五回 心猿钻透阴阳窍 魔王还归大道真
         第七十六回 心神居舍魔归性 木母同降怪体真
         第七十七回 群魔欺本性 一体拜真如
         第七十八回 比丘怜子遣阴神 金殿识魔谈道德
         第七十九回 寻洞擒妖逢老寿 当朝正主救婴儿
         第八 十回 姹女育阳求配偶 心猿护主识妖邪
         第八十一回 镇海寺心猿知怪 黑松林三众寻师
         第八十二回 姹女求阳 元神护道
         第八十三回 心猿识得丹头 姹女还归本性
         第八十四回 难灭伽持圆大觉 法王成正体天然
         第八十五回 心猿妒木母 魔主计吞禅
         第八十六回 木母助威征怪物 金公施法灭妖邪
         第八十七回 凤仙郡冒天致旱 孙大圣劝善施霖
         第八十八回 禅到玉华施法会 心猿木母授门人
         第八十九回 黄狮精虚设钉钯宴 金木土计闹豹头山
         第九十回 师狮授受同归一 盗道缠禅静九灵
         第九十一回 金平府元夜观灯 玄英洞唐僧供状
         第九十二回 三僧大战青龙山 四星挟捉犀牛怪
         第九十三回 给孤园问古谈因 天竺国朝王遇偶
         第九十四回 四僧宴乐御花园 一怪空怀情欲喜
         第九十五回 假合形骸擒玉兔 真阴归正会灵元
         第九十六回 寇员外喜待高僧 唐长老不贪富贵
         第九十七回 金酬外护遭魔毒 圣显幽魂救本原
         第九十八回 猿熟马驯方脱壳 功成行满见真如
         第九十九回 九九数完魔灭尽 三三行满道归根
         第一百回 径回东土 五圣成真
         校点后记
         * pages : 1198
         * images : {"small":"https://img3.doubanio.com/view/subject/s/public/s1627374.jpg","large":"https://img3.doubanio.com/view/subject/l/public/s1627374.jpg","medium":"https://img3.doubanio.com/view/subject/m/public/s1627374.jpg"}
         * alt : https://book.douban.com/subject/1029553/
         * id : 1029553
         * publisher : 人民文学出版社
         * isbn10 : 7020008739
         * isbn13 : 9787020008735
         * title : 西游记（全二册）
         * url : https://api.douban.com/v2/book/1029553
         * alt_title :
         * author_intro : 吴承恩，字汝忠，号射阳山人。淮安府山阳县（今江苏淮安）人。他生活在明代中叶，约当公元1500年到1582年间；出生在一个由文职小官僚而沦落为小商人的家庭。他极好读书，但常遭官府吏胥敲诈，对社会现实颇为不满。
         * summary : 《西游记》主要描写的是孙悟空保唐僧西天取经，历经九九八十一难的故事。唐僧取经是历史上一件真实的事。大约距今一千三百多年前，即唐太宗贞观元年（627），年仅25岁的青年和尚玄奘离开京城长安，只身到天竺（印度）游学。他从长安出发后，途经中亚、阿富汗、巴基斯坦，历尽艰难险阻，最后到达了印度。他在那里学习了两年多，并在一次大型佛教经学辩论会任主讲，受到了赞誉。贞观十九年（645）玄奘回到了长安，带回佛经657部。他这次西天取经，前后十九年，行程几万里，是一次传奇式的万里长征，轰动一时。后来玄奘口述西行见闻，由弟子辩机辑录成《大唐西域记》十二卷。但这部书主要讲述了路上所见各国的历史、地理及交通，没有什么故事。及到他的弟子慧立、彦琮撰写的《大唐大慈恩寺三藏法师传》，则为玄奘的经历增添了许多神话色彩，从此，唐僧取经的故事便开始在民间广为流传。南宋有《大唐三藏取经诗话》，金代院本有《唐三藏》、《蟠桃会》等，元杂剧有吴昌龄的《唐三藏西天取经》、无名氏的《二郎神锁齐大圣》等，这些都为《西游记》的创作奠定了基础。吴承恩也正是在民间传说和话本、戏曲的基础上，经过艰苦的再创造，完成了这部令中华民族为之骄傲的伟大大文学巨著。吴承恩，字汝忠，号射阳山人，淮安府山阳（今江苏省淮安市）人。约生于明弘治十三年至正德初年之间（1500—1510），约卒于万历十年（1582）。吴承恩的曾祖父、祖父都是读书人，任过县学的训导、教谕。但到了他父亲吴锐这一辈，由于家贫困，出赘徐家，“遂袭徐氏业，坐肆中”，当起了小商人。尽管如此，吴家却不失读书的传统。据说其父吴锐虽为商人，不仅为人正派，而且好读书，好谈时政，这自然对吴承恩产生较大影响。吴承恩从小就很聪明，很早入了学，少年得志，名满乡里。天启《淮安府志》卷十六说吴承恩“性敏而多慧，博极群书，为诗文，下笔立成。”但成年后的吴承恩却很不顺利，在科举进身的道路上屡遭挫折，到四十多岁才补了一个岁贡生，五十多岁任过浙江长兴县丞，后又担任过荆王府纪善，这是同县丞级别差不多的闲职。吴承恩创作《西游记》大约是中年以后，或认为是晚年所作，具体时间无法确定。除《西游记》外，他还创作有长诗《二郎搜山图歌》和《禹鼎志》。现存《射阳先生存稿》四卷，包括诗一卷、散文三卷，是吴承恩逝世后由丘度编订而成。 《西游记》全书一百回，从大的结构上看，可分成三个部分。第一回至第八回是第一部分，主要写了孙悟空出世、拜师、大闹天宫，这是全书最精彩的章节，热闹非凡，孙悟空上天入地好一顿折腾，将他的反抗性格表现得淋漓尽致。第八回至第十二回是第二部分，主要写了唐僧的出身及取经的缘由。第十三回至最后一回是第三部分，主要写唐僧西天取经，路上先后收了孙悟空、猪八戒、沙和尚三个徒弟，并历经九九八十一难，终于取到了真经，修成了正果。 《西游记》向人们展示了一个绚丽多彩的神魔世界，人们无不在作者丰富而大胆的艺术想象面前惊叹不已。然而，任何一部文学作品都是一定社会生活的反映，作为神魔小说杰出代表的《西游记》亦不例外。正如鲁迅先生在《中国小说史略》中指出，《西游记》“讽刺揶揄则取当时世态，加以铺张描写”。又说：“作者禀性，‘复善谐剧’，故虽述变幻恍忽之事，亦每杂解颐之言，使神魔皆有人情，精魅亦通世故。”的确如此。 通过《西游记》中虚幻的神魔世界，我们处处可以看到现实社会的投影。如在孙悟空的形象创造上，就寄托了作者的理想。 孙悟空那种不屈不挠的斗争精神，奋起金箍棒，横扫一切妖魔鬼怪的大无畏气概，反映了人民的愿望和要求。他代表了一种正义的力量，表现出人民战胜一切困难的必胜信念。又如取经路上遇到的那些妖魔，或是自然灾难的幻化，或是邪恶势力的象征。他们的贪婪、凶残、阴险和狡诈，也正是封建社会里的黑暗势力的特点。不仅如此，玉皇大帝统治的天宫、如来佛祖管辖的西方极乐世界，也都浓浓地涂上了人间社会的色彩。而作者对封建社会最高统治者的态度也颇可玩味，在《西游记》中，简直找不出一个称职的皇帝；至于昏聩无能的玉皇大帝、宠信妖怪的车迟国国王、要将小儿心肝当药引子的比丘国国王，则不是昏君就是暴君。对这些形象的刻画，即使是信手拈来，也无不具有很强的现实意义。 《西游记》不仅有较深刻的思想内容，艺术上也取得了很高的成就。它以丰富奇特的艺术想象、生动曲折的故事情节，栩栩如生的人物形象，幽默诙谐的语言，构筑了一座独具特色的《西游记》艺术宫殿。但我认为，《西游记》在艺术上的最大成就，是成功地创造了孙悟空、猪八戒这两个不朽的艺术形象。 孙悟空是《西游记》中第一主人公，是个非常了不起的英雄。他有无穷的本领，天不怕地不怕，具有不屈的反抗精神。他有着大英雄的不凡气度，也有爱听恭维话的缺点。他机智勇敢又诙谐好闹。而他最大的特点就是敢斗。与至高至尊的玉皇大帝敢斗，楞是叫响了“齐天大圣”的美名；与妖魔鬼怪敢斗，火眼金睛决不放过一个妖魔，如意金箍棒下决不对妖魔留情；与一切困难敢斗，决不退却低头。这就是孙悟空，一个光彩夺目的神话英雄。说到猪八戒，他的本事比孙悟空可差远了，更谈不上什么光辉高大，但这个形象同样刻画得非常好。 猪八戒是一个喜剧形象，他憨厚老实，有力气，也敢与妖魔作斗争，是孙悟空第一得力助手。但他又满身毛病，如好吃，好占小便宜，好女色，怕困难，常常要打退堂鼓，心里老想着高老庄的媳妇；他有时爱撒个谎，可笨嘴拙腮的又说不圆；他还时不时地挑拨唐僧念紧箍咒，让孙悟空吃点苦头；他甚至还藏了点私房钱，塞在耳朵里。他的毛病实在多，这正是小私有者的恶习。作者对猪八戒缺点的批评是很严厉的，但又是善意的。他并不是一个被否定的人物，因此人们并不厌恶猪八戒，相反却感到十分真实可爱。唐僧的形象写得也不错，但比起孙悟空、猪八戒来，则要逊色得多。沙僧更是缺少鲜明的性格特点，这不能不说是《西游记》的缺憾。尽管如此，《西游记》在艺术上取得的成就仍是十分惊人的，孙悟空、猪八戒这两个形象，以其鲜明的个性特征，在中国文学史上立起了一座不朽的艺术丰碑。
         * series : {"id":"106","title":"中国古典文学读本丛书"}
         * price : 47.20元
         */

        private RatingBean rating;
        private String subtitle;
        private String pubdate;
        private String origin_title;
        private String image;
        private String binding;
        private String catalog;
        private String pages;
        private ImagesBean images;
        private String alt;
        private String id;
        private String publisher;
        private String isbn10;
        private String isbn13;
        private String title;
        private String url;
        private String alt_title;
        private String author_intro;
        private String summary;
        private SeriesBean series;
        private String price;
        private List<String> author;
        private List<TagsBean> tags;
        private List<String> translator;

        @Override
        public String toString() {
            return "BooksBean{" +
                    "rating=" + rating +
                    ", subtitle='" + subtitle + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", origin_title='" + origin_title + '\'' +
                    ", image='" + image + '\'' +
                    ", binding='" + binding + '\'' +
                    ", catalog='" + catalog + '\'' +
                    ", pages='" + pages + '\'' +
                    ", images=" + images +
                    ", alt='" + alt + '\'' +
                    ", id='" + id + '\'' +
                    ", publisher='" + publisher + '\'' +
                    ", isbn10='" + isbn10 + '\'' +
                    ", isbn13='" + isbn13 + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", alt_title='" + alt_title + '\'' +
                    ", author_intro='" + author_intro + '\'' +
                    ", summary='" + summary + '\'' +
                    ", price='" + price + '\'' +
                    ", author=" + author +
                    ", tags=" + tags +
                    ", translator=" + translator +
                    '}';
        }

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public String getOrigin_title() {
            return origin_title;
        }

        public void setOrigin_title(String origin_title) {
            this.origin_title = origin_title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBinding() {
            return binding;
        }

        public void setBinding(String binding) {
            this.binding = binding;
        }

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getIsbn10() {
            return isbn10;
        }

        public void setIsbn10(String isbn10) {
            this.isbn10 = isbn10;
        }

        public String getIsbn13() {
            return isbn13;
        }

        public void setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getAuthor_intro() {
            return author_intro;
        }

        public void setAuthor_intro(String author_intro) {
            this.author_intro = author_intro;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public SeriesBean getSeries() {
            return series;
        }

        public void setSeries(SeriesBean series) {
            this.series = series;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<String> getAuthor() {
            return author;
        }

        public void setAuthor(List<String> author) {
            this.author = author;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<String> getTranslator() {
            return translator;
        }

        public void setTranslator(List<String> translator) {
            this.translator = translator;
        }

        public static class RatingBean {
            /**
             * max : 10
             * numRaters : 28470
             * average : 8.8
             * min : 0
             */

            private int max;
            private int numRaters;
            private String average;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getNumRaters() {
                return numRaters;
            }

            public void setNumRaters(int numRaters) {
                this.numRaters = numRaters;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/subject/s/public/s1627374.jpg
             * large : https://img3.doubanio.com/view/subject/l/public/s1627374.jpg
             * medium : https://img3.doubanio.com/view/subject/m/public/s1627374.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class SeriesBean {
            /**
             * id : 106
             * title : 中国古典文学读本丛书
             */

            private String id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class TagsBean {
            /**
             * count : 3759
             * name : 古典文学
             * title : 古典文学
             */

            private int count;
            private String name;
            private String title;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}
