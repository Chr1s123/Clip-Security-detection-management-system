import sys
from PIL import Image
import torch
import clip
import json

def analyze_image(image_path):
    # 加载CLIP模型
    model, preprocess = clip.load("ViT-B/32", device="cpu")

    # 准备图片
    image = preprocess(Image.open(image_path)).unsqueeze(0).to("cpu")

    # 定义安全相关的文本描述
    text_descriptions = [
        # 正向（安全）
        "worker wearing safety helmet and properly secured with safety rope in high-altitude work",
        "worker in high-altitude work with safety helmet and harness",
        "worker using safety equipment and secured with rope on a rooftop",
        "worker in a safe high-altitude environment with protective gear",

        # 负向（不安全）
        "worker in high-altitude work without safety helmet",
        "worker in high-altitude work without safety rope",
        "worker in high-altitude work without any safety equipment",
        "worker in a dangerous high-altitude environment without protective gear"
    ]
    text = clip.tokenize(text_descriptions).to("cpu")

    # 推理
    with torch.no_grad():
        image_features = model.encode_image(image)
        text_features = model.encode_text(text)

        # 计算相似度
        logits_per_image, _ = model(image, text)
        probs = logits_per_image.softmax(dim=-1).cpu().numpy()

    # 获取最高概率的描述及其概率值
    max_prob_index = probs[0].argmax()
    max_prob = probs[0][max_prob_index]
    result = text_descriptions[max_prob_index]

    # 打印匹配结果和概率
#     print(f"Most likely description: {result} (Probability: {max_prob:.2%})")

    # 判定安全与否，并提供具体原因
    if "without safety helmet" in result:
        return "unsafe", "Worker is not wearing a safety helmet, which is a critical safety violation in high-altitude work."
    elif "without safety rope" in result:
        return "unsafe", "Worker is not secured with a safety rope, posing a significant fall risk in high-altitude work."
    elif "without any safety equipment" in result or "dangerous high-altitude environment" in result:
        return "unsafe", "Worker lacks essential safety equipment, making the environment highly dangerous."
    else:
        return "safe", "Worker appears to be in a safe high-altitude environment with proper safety equipment (helmet and rope)."

if __name__ == "__main__":
    image_path = sys.argv[1]
    safety_status, reason = analyze_image(image_path)
    print(f"{safety_status}")
#     print(f"Reason: {reason}")
# if __name__ == "__main__":
#     image_path = sys.argv[1]
#     safety_status, reason = analyze_image(image_path)
#     output = {"status": safety_status, "reason": reason}
#     print(json.dumps(output))