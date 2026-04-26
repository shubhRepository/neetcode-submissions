class Solution {

    public String encode(List<String> strs) {
        int strsLen = strs.size();
        StringBuilder encodeStrs = new StringBuilder();
        for (String str : strs) {
            encodeStrs.append(String.valueOf(str.length()));
            encodeStrs.append('_');
            encodeStrs.append(str);
        }
        return encodeStrs.toString();
    }

    public List<String> decode(String str) {
        int strLen = str.length();
        List<String> strs = new ArrayList<>();
        int idx = 0;
        while (idx < strLen) {
            int lenIdx = lastLenIndex(str, idx);
            int wordLen = Integer.parseInt(str.substring(idx, lenIdx));
            StringBuilder word = new StringBuilder();
            for (int i = lenIdx + 1; i < (lenIdx + 1 + wordLen); i++) {
                word.append(str.charAt(i));
            }
            strs.add(word.toString());
            idx = lenIdx + 1 + wordLen;
        }
        return strs;
    }

    private int lastLenIndex(String str, int begIdx) {
        int lenIdx = begIdx;
        while (str.charAt(lenIdx) != '_') {
            lenIdx += 1;
        }
        return lenIdx;
    }
}

