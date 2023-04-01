<footer class="footer">
  <div class="container-footer">
    <div class="row-header">
      <div class="footer-col">
        <h4 class="footer-title">Company</h4>
        <ul>
          <li><a href="#">Branch 1 Address: No1 Road ABC City</a></li>
          <li><a href="#">Branch 1 Address: No1 Road ABC City</a></li>
          <li><a href="#">Hotline: 123</a></li>
        </ul>
      </div>
      <div class="footer-col">
        <h4 class="footer-title">Detail Information</h4>
        <ul>
          <li><a href="#">Mr Dat: Email: 20110360@gmail.com</a></li>
          <li><a href="#">Mr Duong: Email:19146085@gmail.com</a></li>
          <li><a href="#">Mr Phuoc: Email 20110392@gmail.com</a></li>
          
          <li><a href="#">Mrs Thanh: Email 19110123@gmail.com</a></li>
          
          <div class="social-links">
            <a href="#"><i class="fab fa-facebook-f"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
            <a href="#"><i class="fab fa-instagram"></i></a>
            <a href="#"><i class="fab fa-linkedin-in"></i></a>
          </div>
        </ul>
      </div>
      <div class="footer-col">
        <h4 class="footer-title">Contact</h4>
        <ul>
          <li>
            <p class="message-footer">
              Lien he vo chung toi qua dia chi Email bang cach dien Email cua
              bam vao day
            </p>
          </li>
          <input
            type="text"
            name=""
            value=""
            class="form-control-contact"
            placeholder="Contact us"
          />
        </ul>
      </div>
    </div>
  </div>
</footer>
<script src="https://d2j7grvgvx4mfm.cloudfront.net/lex-web-ui-loader.min.js"></script>
<script>
  const loaderOpts = {
    baseUrl: "https://d2j7grvgvx4mfm.cloudfront.net/",
    shouldLoadMinDeps: true,
  };
  const loader = new ChatBotUiLoader.IframeLoader(loaderOpts);
  const chatbotUiConfig = {
    ui: {
      parentOrigin: "http://localhost:8080/bookshop",
      toolbarTitle: "Order Flowers",
      toolbarLogo: "",
      positiveFeedbackIntent: "Thumbs up",
      negativeFeedbackIntent: "Thumbs down",
      helpIntent: "Help",
      enableLogin: true,
      enableLiveChat: false,
      forceLogin: false,
      AllowSuperDangerousHTMLInMessage: true,
      shouldDisplayResponseCardTitle: false,
      saveHistory: false,
      enableSFX: false,
      minButtonContent: "",
      hideInputFieldsForButtonResponse: false,
      pushInitialTextOnRestart: false,
      directFocusToBotInput: false,
      showDialogStateIcon: false,
      backButton: false,
      messageMenu: true,
      hideButtonMessageBubble: false,
    },
    iframe: {
      iframeOrigin: "https://d2j7grvgvx4mfm.cloudfront.net",
      shouldLoadIframeMinimized: false,
      iframeSrcPath: "/index.html#/?lexWebUiEmbed=true",
    },
  };
  loader.load(chatbotUiConfig).catch(function (error) {
    console.error(error);
  });
</script>
