FROM mongo

COPY places.json init.json
COPY init.sh init.sh

ENTRYPOINT ["sh", "/init.sh"]