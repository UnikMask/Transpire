regex = r'(\|{2}|&{2}|\*{2}|!=|={2}|>=|;|:|\{|}|\+|-|\*|/|\[|\]|>|<|=|\(|\)|\.)'
contents = re.sub(regex, ' \g<0> ', file)